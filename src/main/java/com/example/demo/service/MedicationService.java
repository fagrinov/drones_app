package com.example.demo.service;

import java.util.List;

import com.example.demo.shared.dto.MedicationDto;

public interface MedicationService {

	MedicationDto createMedication(MedicationDto medication);
	List<MedicationDto> getMedicationItems(String serial);
	MedicationDto updateMedication (MedicationDto medication);
}
