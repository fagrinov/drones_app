package com.example.demo.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class MedicationEntity implements Serializable {

	private static final long serialVersionUID = 4630888492087474399L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private double weight;

	@Column(nullable = false)
	private String code;

	@Column(nullable = true)
	private String imagePath;

	@Lob
	@Column(nullable = true)
	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "droneId")
	private DroneEntity droneEntity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public DroneEntity getDroneEntity() {
		return droneEntity;
	}

	public void setDroneEntity(DroneEntity droneEntity) {
		this.droneEntity = droneEntity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
