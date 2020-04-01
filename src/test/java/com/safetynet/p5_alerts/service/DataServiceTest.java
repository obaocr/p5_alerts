package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.FireStationDaoImpl;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;

class DataServiceTest {

	@Test
	void loadDataPersonTest() throws IOException {
		DataService dataService = new DataServiceImpl("data_test.json");
		dataService.loadData();
		PersonDao personDao = new PersonDaoImpl();
		FireStationDao fireStationDao = new FireStationDaoImpl();
		MedicalRecordDao medialRecordDao = new MedicalRecordDaoImpl();
		int nbPersons = personDao.getAll().size();
		int nbFirestations = fireStationDao.getAll().size();
		int nbMedicalrecords = medialRecordDao.getAll().size();

		System.out.println("** loadDataPersonTest / nbFirestations : " + nbFirestations);
		System.out.println("** loadDataPersonTest / nbPersons : " + nbPersons);
		System.out.println("** loadDataPersonTest / nbMedicalrecords : " + nbMedicalrecords);

		assertTrue(nbPersons > 0);
		assertTrue(nbFirestations > 0);
		assertTrue(nbMedicalrecords > 0);
	}

}
