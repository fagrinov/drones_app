package com.example.demo.shared.dto;

import java.io.Serializable;
import java.util.List;

public class DroneDto implements Serializable{

	private static final long serialVersionUID = 8721943952606264250L;

	private String serial;
	
	private int model;
	
	private double weight;
	
	private int battary;
	
	private int state;
	
	private List<MedicationDto> medications ;
	
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getBattary() {
		return battary;
	}

	public void setBattary(int battary) {
		this.battary = battary;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<MedicationDto> getMedications() {
		return medications;
	}

	public void setMedications(List<MedicationDto> medications) {
		this.medications = medications;
	}

}
