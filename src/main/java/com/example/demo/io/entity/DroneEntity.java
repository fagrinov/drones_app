package com.example.demo.io.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class DroneEntity implements Serializable {

	private static final long serialVersionUID = -3261202287741416965L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String serial;
	
	@Column(nullable = false , length = 2)
	private int model;
	
	@Column(nullable = false )
	private double weight;
	
	@Column(nullable = false , length = 3)
	private int battary;
	
	@Column(nullable = false , length = 2)
	private int state;

	@OneToMany(mappedBy = "droneEntity")
    private Set<MedicationEntity> medications = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Set<MedicationEntity> getMedications() {
		return medications;
	}

	public void setMedications(Set<MedicationEntity> medications) {
		this.medications = medications;
	}
	
	
	
	
}
