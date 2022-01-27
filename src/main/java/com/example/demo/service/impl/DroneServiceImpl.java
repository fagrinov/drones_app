package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DroneRepository;
import com.example.demo.enums.DroneStateEnum;
import com.example.demo.io.entity.DroneEntity;
import com.example.demo.service.DroneService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.DroneDto;

@Service
public class DroneServiceImpl implements DroneService{

	@Autowired
	DroneRepository droneRepository;
	
	@Autowired
	Utils util;

	//registering a drone
	@Override
	public DroneDto createDrone(DroneDto droneDto) {
		if(droneDto.getWeight() > 500) throw new RuntimeException("weight more than 500 gram is not allowed");
		if(droneDto.getBattary() > 100) throw new RuntimeException("battary level is a percentatge level with 100 max");
		DroneEntity droneEntity = new DroneEntity();
		BeanUtils.copyProperties(droneDto, droneEntity);
		droneEntity.setSerial(util.generateSerialNumber(20));
		droneEntity.setState(DroneStateEnum.IDLE);
		DroneEntity storedDroneDetails = droneRepository.save(droneEntity);

		DroneDto returnValue = new DroneDto();
		BeanUtils.copyProperties(storedDroneDetails, returnValue);
		return returnValue;
	}

	@Override
	public DroneDto updateDrone(String serial,DroneDto droneDto) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if(droneEntity == null) throw new RuntimeException("there is not a drone with this serial");
		droneEntity.setState(droneDto.getState());
		droneEntity.setBattary(droneDto.getBattary());
		DroneEntity updatedDroneDetails = droneRepository.save(droneEntity);
		DroneDto returnValue = new DroneDto();
		returnValue = new ModelMapper().map(updatedDroneDetails, DroneDto.class);
		return returnValue;
	}

	//checking available drones for loading
	@Override
	public List<DroneDto> getIdleDrones() {
		return convertToDtos(droneRepository.findByState(DroneStateEnum.IDLE));
	}

	//check drone battery level for a given drone
	@Override
	public DroneDto getDrone(String serial) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if(droneEntity == null) throw new RuntimeException("there is not a drone with this serial");
		DroneDto returnedDroneDto = new DroneDto ();
		BeanUtils.copyProperties(droneEntity, returnedDroneDto);
		return returnedDroneDto;
	}
	
	private List<DroneDto> convertToDtos(List<DroneEntity> droneEntities){
		List<DroneDto> returnedDrones = new ArrayList<DroneDto>();
		for(int i = 0; i< droneEntities.size(); i++) {
			DroneDto droneDto = new DroneDto ();
			BeanUtils.copyProperties(droneEntities.get(i), droneDto);
			returnedDrones.add(droneDto);
			
		}
		return returnedDrones;
	}
}
