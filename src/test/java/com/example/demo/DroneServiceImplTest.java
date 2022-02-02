package com.example.demo;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.io.entity.DroneEntity;
import com.example.demo.service.impl.DroneServiceImpl;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.DroneDto;

class DroneServiceImplTest {

	@Mock
	DroneRepository droneRepository;
	
	@Mock
	Utils util;
	
	@InjectMocks
	DroneServiceImpl droneService;
	
	DroneEntity droneEntity;
	
	String serial = "joieg877ergerg";
	
	Date auditDate = new Date();
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "31-08-1982 10:20:56";
		auditDate = sdf.parse(dateInString);
		droneEntity = new DroneEntity();
		droneEntity.setSerial(serial);
		droneEntity.setState(1);
		droneEntity.setModel(1);
		droneEntity.setWeight(300);
		droneEntity.setBattery(80);
		droneEntity.setAuditDate(auditDate);
	}

	@Test
	void testGetDrone() {
		
		
		when(droneRepository.findBySerial(ArgumentMatchers.anyString())).thenReturn(droneEntity);
		
		DroneDto droneDto = droneService.getDrone(serial);
		Assertions.assertNotNull(droneDto);
		Assertions.assertEquals(300,droneDto.getWeight());
	}
	
	@Test
	void testGetDrone_RunTimeException() {
		when(droneRepository.findBySerial(ArgumentMatchers.anyString())).thenReturn(null);
		Assertions.assertThrows(RuntimeException.class,() -> {droneService.getDrone("joieg877ergerg");});
	}

	@Test
	void testCreateUser() {
		DroneDto droneDto = new DroneDto();
		droneDto.setModel(1);
		droneDto.setWeight(300);
		droneDto.setBattery(80);
		when(util.generateSerialNumber(ArgumentMatchers.anyInt())).thenReturn(serial);
		droneEntity.setAuditDate(new Date());
		when(droneRepository.save(ArgumentMatchers.any(DroneEntity.class))).thenReturn(droneEntity);
		DroneDto createdDroneDto = droneService.createDrone(droneDto);
		Assertions.assertNotNull(createdDroneDto);
		Assertions.assertNotNull(createdDroneDto.getSerial());
		Assertions.assertEquals(1,createdDroneDto.getState());
		Assertions.assertEquals(1,createdDroneDto.getModel());
		Assertions.assertEquals(300,createdDroneDto.getWeight());
		Assertions.assertEquals(80,createdDroneDto.getBattery());		
	}
}
