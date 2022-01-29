package com.example.demo.ui.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DroneService;
import com.example.demo.service.MedicationService;
import com.example.demo.shared.dto.DroneDto;
import com.example.demo.shared.dto.MedicationDto;
import com.example.demo.ui.model.request.DroneDetailsRequestModel;
import com.example.demo.ui.model.request.MedicationDetailsRequestModel;
import com.example.demo.ui.model.response.DroneRest;
import com.example.demo.ui.model.response.MedicationRest;

@RestController
@RequestMapping("drones") //http://localhost.com:8080/drones
public class DroneController {
	
	@Autowired
	DroneService droneService;
	
	@Autowired
	MedicationService medService;
	
	Logger logger = LoggerFactory.getLogger(DroneController.class);
	//checking available drones for loading
	@GetMapping
	public List<DroneRest> getIdleDrones() {
		List<DroneDto> droneDto = droneService.getIdleDrones();
		return convertToResponse(droneDto);
	}
	
	//check drone battery level for a given drone
	@GetMapping(path = "/{serial}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public DroneRest getDrone(@PathVariable String serial) {
		DroneRest droneRest = new DroneRest();
		DroneDto returnedDroneDto = droneService.getDrone(serial);
		BeanUtils.copyProperties(returnedDroneDto, droneRest);
		return droneRest;
	}
	
	@GetMapping(path = "/{serial}/{medications}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<MedicationRest> getMedications(@PathVariable String serial, @PathVariable String medications) {
		List<MedicationDto> medicationDtos = medService.getMedicationItems(serial);
		DroneDto droneDto = new DroneDto();
		droneDto.setMedications(medicationDtos);
		return new ModelMapper().map(droneDto, DroneRest.class).getMedications();
	}
	
	//registering a drone
	@PostMapping
	public DroneRest createDrone(@RequestBody DroneDetailsRequestModel droneDetail) {
		DroneDto droneDto = new DroneDto();
		BeanUtils.copyProperties(droneDetail, droneDto);
		DroneDto createdDrone = droneService.createDrone(droneDto);
		DroneRest returnValue = new DroneRest();
		BeanUtils.copyProperties(createdDrone, returnValue);
		return returnValue;
	}
	
	// updating drone state or battery capacity
	@PutMapping(path = "/{serial}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public DroneRest updateDrone(@PathVariable String serial,@RequestBody DroneDetailsRequestModel droneDetail) {
		DroneRest returnValue = new DroneRest();
		DroneDto droneDto = new DroneDto();
		droneDto = new ModelMapper().map(droneDetail, DroneDto.class);
		DroneDto updatedDrone = droneService.updateDrone(serial,droneDto);
		BeanUtils.copyProperties(updatedDrone, returnValue);
		return returnValue;
	
	}
	
	//loading a drone with medication items;
	@PutMapping(path = "/{serial}/{load}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public DroneRest loadDeleiverMedicationItems(@PathVariable String serial,@PathVariable String load,@RequestBody List<MedicationDetailsRequestModel> medications) {
		DroneRest returnValue = new DroneRest();
		DroneDto droneDto = new DroneDto();
		if(load.equals("1")) {
		List<String> medicationCodes  = medications.stream()
			    .map(MedicationDetailsRequestModel::getCode)
			    .collect(Collectors.toList());
			droneDto = droneService.loadMedicationItems(serial, medicationCodes);
		} else {
			droneDto = droneService.deleveringMedicationItems(serial);
		}
		returnValue = new ModelMapper().map(droneDto, DroneRest.class);
		return returnValue;
	
	}
	
	@Scheduled( fixedRate = 1, timeUnit = TimeUnit.MINUTES)
	public void run() {
		droneService.getAllDrones().forEach(drone -> 
		logger.info("Drone "+ drone.getSerial()+" ,Battery Level "+ drone.getBattary()
		+" at "+ Calendar.getInstance().getTime()));
	}
	@DeleteMapping
	public String deleteDrone() {
		return "delete patient";
	}
	
	private List<DroneRest> convertToResponse(List<DroneDto> droneDtos){
		List<DroneRest> returnedDrones = new ArrayList<DroneRest>();
		for(int i = 0; i< droneDtos.size(); i++) {
			DroneRest droneRest = new DroneRest ();
			BeanUtils.copyProperties(droneDtos.get(0), droneRest);
			returnedDrones.add(droneRest);
			
		}
		return returnedDrones;
	}
}
