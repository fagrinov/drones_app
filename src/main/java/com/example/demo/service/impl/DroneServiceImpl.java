package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DroneRepository;
import com.example.demo.MedicationRepository;
import com.example.demo.enums.DroneStateEnum;
import com.example.demo.io.entity.DroneEntity;
import com.example.demo.io.entity.MedicationEntity;
import com.example.demo.service.DroneService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.DroneDto;

@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	DroneRepository droneRepository;

	@Autowired
	MedicationRepository medicationRepository;

	@Autowired
	Utils util;

	// registering a drone
	@Override
	public DroneDto createDrone(DroneDto droneDto) {
		if (droneDto.getWeight() > 500)
			throw new RuntimeException("weight more than 500 gram is not allowed");
		if (droneDto.getBattery() > 100)
			throw new RuntimeException("battary level is a percentatge level with 100 max");
		DroneEntity droneEntity = new DroneEntity();
		BeanUtils.copyProperties(droneDto, droneEntity);
		droneEntity.setSerial(util.generateSerialNumber(20));
		droneEntity.setState(DroneStateEnum.IDLE);
		droneEntity.setAuditDate(new Date());
		DroneEntity storedDroneDetails = droneRepository.save(droneEntity);

		DroneDto returnValue = new DroneDto();
		BeanUtils.copyProperties(storedDroneDetails, returnValue);
		return returnValue;
	}

	// update drone states with idle, returning , delivering
	@Override
	public DroneDto updateDrone(String serial, DroneDto droneDto) {
		if (droneDto.getBattery() > 100)
			throw new RuntimeException("battary level is a percentatge level with 100 max");
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if (droneEntity == null)
			throw new RuntimeException("there is not a drone with this serial");
		if (droneDto.getState() == DroneStateEnum.IDLE || droneDto.getState() == DroneStateEnum.RETURNING
				|| droneDto.getState() == DroneStateEnum.DELIVERING)
			droneEntity.setState(droneDto.getState());
		else if (droneDto.getState() != 0)
			throw new RuntimeException("Medications should be updated first");
		if (droneDto.getBattery() != 0) {
			droneEntity.setBattery(droneDto.getBattery());
			droneEntity.setAuditDate(new Date());
		}
		DroneEntity updatedDroneDetails = droneRepository.save(droneEntity);
		DroneDto returnValue = new DroneDto();
		returnValue = new ModelMapper().map(updatedDroneDetails, DroneDto.class);
		return returnValue;
	}

	// checking available drones for loading
	@Override
	public List<DroneDto> getIdleDrones() {
		return convertToDtos(droneRepository.findByState(DroneStateEnum.IDLE));
	}

	@Override
	public List<DroneDto> getAllDrones() {
		return convertToDtos((List<DroneEntity>) droneRepository.findAll());
	}

	// check drone battery level for a given drone
	@Override
	public DroneDto getDrone(String serial) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if (droneEntity == null)
			throw new RuntimeException("there is not a drone with this serial");
		DroneDto returnedDroneDto = new DroneDto();
		BeanUtils.copyProperties(droneEntity, returnedDroneDto);
		return returnedDroneDto;
	}

	// loading a drone with medication items
	@Override
	public DroneDto loadMedicationItems(String serial, List<String> medicationCodes) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if (droneEntity == null)
			throw new RuntimeException("there is not a drone with this serial");
		if (droneEntity.getBattery() < 25)
			throw new RuntimeException("Battery Level is under 25");
		droneEntity.setState(DroneStateEnum.LOADING);
		droneRepository.save(droneEntity);
		List<MedicationEntity> medicationEntities = new ArrayList<MedicationEntity>();
		double totalWeight = 0;
		for (int i = 0; i < medicationCodes.size(); i++) {
			MedicationEntity medicationEntity = medicationRepository.findByCode(medicationCodes.get(i));
			totalWeight += medicationEntity.getWeight();
			if (totalWeight > droneEntity.getWeight())
				break;
			if (medicationEntity != null) {
				medicationEntity.setDroneEntity(droneEntity);
				medicationEntities.add(medicationEntity);
			}
		}
		droneEntity.setMedications(medicationEntities);
		droneEntity.setState(DroneStateEnum.LOADED);
		droneRepository.save(droneEntity);
		DroneDto loadedDrone = new ModelMapper().map(droneEntity, DroneDto.class);
		return loadedDrone;
	}

	@Override
	public DroneDto deleveringMedicationItems(String serial) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if (droneEntity == null)
			throw new RuntimeException("there is not a drone with this serial");
		if (droneEntity.getState() != DroneStateEnum.DELIVERING)
			throw new RuntimeException("this drone is not in delivering state");
		droneEntity.setState(DroneStateEnum.RETURNING);
		droneEntity.getMedications().forEach(m -> m.setDroneEntity(null));
		droneEntity.setMedications(null);
		return new ModelMapper().map(droneEntity, DroneDto.class);
	}

	private List<DroneDto> convertToDtos(List<DroneEntity> droneEntities) {
		List<DroneDto> returnedDrones = new ArrayList<DroneDto>();
		for (int i = 0; i < droneEntities.size(); i++) {
			DroneDto droneDto = new DroneDto();
			BeanUtils.copyProperties(droneEntities.get(i), droneDto);
			returnedDrones.add(droneDto);

		}
		return returnedDrones;
	}

}
