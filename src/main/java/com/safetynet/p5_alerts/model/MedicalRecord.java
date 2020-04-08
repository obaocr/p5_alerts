package com.safetynet.p5_alerts.model;

import java.util.Date;
import java.util.List;

/**
 * Model MedicalRecord
 */
public class MedicalRecord {
	private String firstname;
	private String lastname;
	private Date birthDate;
	private List<String> medications;
	private List<String> allergies;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthDate() {
		return birthDate != null ? new Date(birthDate.getTime()) : null;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate != null ? new Date(birthDate.getTime()) : null;
	}

	public List<String> getMedications() {
		return medications;
	}

	public void setMedications(List<String> medications) {
		this.medications = medications;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	

}

