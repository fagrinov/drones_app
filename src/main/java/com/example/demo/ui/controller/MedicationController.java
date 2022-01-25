package com.example.demo.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.MedicationService;
import com.example.demo.ui.model.request.MedicationDetailsRequestModel;
import com.example.demo.ui.model.response.MedicationRest;

@RestController
@RequestMapping("medications") //http://localhost.com:8080/medications
public class MedicationController {
	@Autowired
	MedicationService medicationService;
	@GetMapping
	public List<MedicationRest> getMedications() {
		return null;
	}
	
	@PostMapping
	public MedicationRest createMedication(@RequestBody MedicationDetailsRequestModel medicDetail) {
		
		return null;
	}
	
	@PutMapping
	public MedicationRest updateMedication(@RequestBody MedicationDetailsRequestModel medicDetail) {
		
		return null;
	}
	
	@DeleteMapping
	public String deleteMedication() {
		return "delete Medication";
	}
}
