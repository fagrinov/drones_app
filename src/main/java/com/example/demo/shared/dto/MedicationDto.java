package com.example.demo.shared.dto;

import java.io.Serializable;

public class MedicationDto implements Serializable {

	private static final long serialVersionUID = -82762295581631414L;

	
	private String name;
	
	private double weight;
	
	private String code;
	
	private String imagePath;
	
	private byte[] image;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
