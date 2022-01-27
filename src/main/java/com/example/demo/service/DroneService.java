package com.example.demo.service;


import java.util.List;

import com.example.demo.shared.dto.DroneDto;

public interface DroneService {
	
	DroneDto createDrone(DroneDto droneDto);
	DroneDto updateDrone(String serial,DroneDto droneDto);
	List<DroneDto> getIdleDrones();
	DroneDto getDrone(String serial);
}
