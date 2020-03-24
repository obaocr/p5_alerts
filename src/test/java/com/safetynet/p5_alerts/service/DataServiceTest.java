package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DataServiceTest {

	@Test
	void loadDataPersonTest() {
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
		int nbPersons = ds.getPersons().size();
		int nbFirestations = ds.getFirestations().size();
		int nbMedicalrecords = ds.getMedicalrecords().size();

		assertTrue(nbPersons == 23);
		assertTrue(nbFirestations == 13);
		assertTrue(nbMedicalrecords == 23);
	}

}
