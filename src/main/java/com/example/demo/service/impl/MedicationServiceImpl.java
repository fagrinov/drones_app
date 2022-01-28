package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DroneRepository;
import com.example.demo.MedicationRepository;
import com.example.demo.io.entity.DroneEntity;
import com.example.demo.io.entity.MedicationEntity;
import com.example.demo.service.MedicationService;
import com.example.demo.shared.Utils;
import com.example.demo.shared.dto.DroneDto;
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
	public MedicationDto createMedication(MedicationDto medicationDto) {
		if(!util.validateMedicationName(medicationDto.getName())) throw new RuntimeException("only allowed dashs, underscores, letters and numbers");
		MedicationEntity  medicationEntity = new  MedicationEntity();
		BeanUtils.copyProperties(medicationDto, medicationEntity);
		medicationEntity.setCode(util.generateCode(10));
		MedicationEntity storedMedicationDetails = medicationRepository.save(medicationEntity);
		MedicationDto returnValue = new MedicationDto();
		BeanUtils.copyProperties(storedMedicationDetails, returnValue);
		return returnValue;
	}

	@Override
	public MedicationDto updateMedication(MedicationDto medication) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicationDto> getMedicationItems(String serial) {
		DroneEntity droneEntity = droneRepository.findBySerial(serial);
		if(droneEntity == null) throw new RuntimeException("there is not a drone with this serial");
		if(droneEntity.getMedications() == null ) throw new RuntimeException("there is no Medications for this drone");
		DroneDto droneDto = new ModelMapper().map(droneEntity, DroneDto.class);
		return droneDto.getMedications();
	}
	
	
}
