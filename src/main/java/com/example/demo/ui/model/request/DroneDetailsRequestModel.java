package com.example.demo.ui.model.request;

import java.util.Date;
import java.util.List;

public class DroneDetailsRequestModel {
	
	private String serial;
	
	private int model;
	
	private double weight;
	
	private int battery;
	
	private int state;
	
	private Date auditDate;
	
	private List<MedicationDetailsRequestModel> medications ;

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

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<MedicationDetailsRequestModel> getMedications() {
		return medications;
	}

	public void setMedications(List<MedicationDetailsRequestModel> medications) {
		this.medications = medications;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
	
	
}
