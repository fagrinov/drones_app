package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DroneRepository;
import com.example.demo.service.DroneService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.DroneDto;

@Service
public class DroneServiceImpl implements DroneService{

	@Autowired
	DroneRepository droneRepository;
	
	@Autowired
	Utils util;

	@Override
	public DroneDto createDrone(DroneDto droneDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
