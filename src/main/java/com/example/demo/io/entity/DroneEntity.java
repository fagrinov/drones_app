package com.example.demo.io.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


@Audited
@Entity
public class DroneEntity implements Serializable {

	private static final long serialVersionUID = -3261202287741416965L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false, length = 100, unique = true)
	private String serial;
	
	@NotAudited
	@Column(nullable = false , length = 2)
	private int model;
	
	@NotAudited
	@Column(nullable = false )
	private double weight;
	
	@Column(nullable = false , length = 3)
	private int battery;
	
	@NotAudited
	@Column(nullable = false , length = 2)
	private int state;

	@NotAudited
	@OneToMany(mappedBy = "droneEntity", cascade=CascadeType.ALL)
    private List<MedicationEntity> medications;
	
	@Column(nullable = false)
	private Date auditDate;

	
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

	public List<MedicationEntity> getMedications() {
		return medications;
	}

	public void setMedications(List<MedicationEntity> medications) {
		this.medications = medications;
	}
	
	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}
