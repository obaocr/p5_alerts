package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;

class DataServiceTest {

	@Test
	void loadDataPersonTest() {
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons = personDao.getAll().size();
		//int nbFirestations = ds.getFirestations().size();
		//int nbMedicalrecords = ds.getMedicalrecords().size();

		assertTrue(nbPersons > 0);
		//assertTrue(nbFirestations == 13);
		//assertTrue(nbMedicalrecords == 23);
	}

}
