package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.FireStationDaoImpl;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;

@ContextConfiguration
@SpringBootTest
class DataServiceTest {

	@Autowired
	DataService dataService;
	
	@Autowired
	PersonDao personDao;
	
	@Autowired
	FireStationDao fireStationDao;
	
	@Autowired
	MedicalRecordDao medialRecordDao;

	@Test
	void loadDataPersonTest() throws IOException {
		dataService.loadData();
		assertTrue(personDao.getAll().size() == 23);
		assertTrue(fireStationDao.getAll().size() == 13);
		assertTrue(medialRecordDao.getAll().size() == 23);
	}

}
