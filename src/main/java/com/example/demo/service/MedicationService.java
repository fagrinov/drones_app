package com.example.demo.service;

import com.example.demo.shared.dto.MedicationDto;

public interface MedicationService {

	MedicationDto createAppoint(MedicationDto medication);
	MedicationDto updateAppoint (MedicationDto medication);
}
