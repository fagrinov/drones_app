package com.example.demo.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DroneService;
import com.example.demo.ui.model.request.DroneDetailsRequestModel;
import com.example.demo.ui.model.response.DroneRest;

@RestController
@RequestMapping("drones") //http://localhost.com:8080/drones
public class DroneController {
	
	@Autowired
	DroneService droneService;
	@GetMapping
	public String getDrone() {
		return "get drone detail";
	}
	
	@PostMapping
	public DroneRest createDrone(@RequestBody DroneDetailsRequestModel droneDetail) {
		return null;
	}
	
	@PutMapping
	public DroneRest updateDrone() {
		return null;
	}
	
	@DeleteMapping
	public String deleteDrone() {
		return "delete patient";
	}
}
