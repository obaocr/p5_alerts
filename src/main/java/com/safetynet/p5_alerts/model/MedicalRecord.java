package com.safetynet.p5_alerts.model;

import java.util.Date;
import java.util.List;

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
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	@Override
	public String toString() {
		return "MedicalRecord [firstname=" + firstname + ", lastname=" + lastname + ", birthDate=" + birthDate
				+ ", medications=" + medications + ", allergies=" + allergies + "]";
	}
	
	

}

