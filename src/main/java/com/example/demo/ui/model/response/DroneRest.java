package com.example.demo.ui.model.response;

import java.util.List;

public class DroneRest {

private String serial;
	
	private int model;
	
	private double weight;
	
	private int battary;
	
	private int state;
	
	private List<MedicationRest> medications ;

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

	public List<MedicationRest> getMedications() {
		return medications;
	}

	public void setMedications(List<MedicationRest> medications) {
		this.medications = medications;
	}
}
