package com.example.demo.ui.model.request;

public class DroneDetailsRequestModel {
	
	private String serial;
	
	private String model;
	
	private double weight;
	
	private int battary;
	
	private String state;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
