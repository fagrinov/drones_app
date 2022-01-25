package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DroneRepository;
import com.example.demo.MedicationRepository;
import com.example.demo.service.MedicationService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.MedicationDto;

@Service
public class MedicationServiceImpl implements MedicationService{

	@Autowired
	MedicationRepository medicationRepository;
	
	@Autowired
	DroneRepository droneRepository;
	
	@Autowired
	Utils util;
	
	@Override
	public MedicationDto createAppoint(MedicationDto medicationDto) {
		return null;
	}

	@Override
	public MedicationDto updateAppoint(MedicationDto medicationDto) {
		return null;
	}

}
