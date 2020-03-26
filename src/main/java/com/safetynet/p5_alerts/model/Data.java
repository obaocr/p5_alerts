package com.safetynet.p5_alerts.model;

import java.util.List;

public class Data {
	private  List<Person> persons;
	private  List<FireStation> firestations;
	private  List<MedicalRecord> medicalrecords;

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<FireStation> getFireStation() {
		return firestations;
	}

	public void setFireStations(List<FireStation> firestations) {
		this.firestations = firestations;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalrecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalrecords) {
		this.medicalrecords = medicalrecords;
	}
}
