package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;


public interface DataService {

	/**
	 * Method to load data (person, firestation, medicalrecord) into java objects
	 * 
	 * @throws IOException
	 */
	void loadData();

	List<Person> getPersons();

	List<FireStation> getFirestations();

	List<MedicalRecord> getMedicalrecords();

	void savePerson(Person person);
	
	void updatePerson(Person person);
	
	void deletePerson(Person person);

}
