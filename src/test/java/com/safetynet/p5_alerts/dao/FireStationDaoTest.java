package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

class FireStationDaoTest {

	@BeforeAll
	private static void initData() {
		FireStationDao fireStationDao = new FireStationDaoImpl();
		fireStationDao.deleteAll();
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
	}

	@Test
	void getAllTest() {
		FireStationDao fireStationDao = new FireStationDaoImpl();
		int nbItem = fireStationDao.getAll().size();
		assertTrue(nbItem > 1);
	}

	@Test
	void addFireStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address");
		fireStation.setStation("100");
		FireStationDao fireStationDao = new FireStationDaoImpl();
		int nbItem = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		assertTrue(nbItem + 1 == fireStationDao.getAll().size());

	}
	
	@Test
	void deleteFireStationAddressStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
		FireStationDao fireStationDao = new FireStationDaoImpl();
		int nbItem1 = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		int nbItem2 = fireStationDao.getAll().size();
		//
		FireStation fireStation2 = new FireStation();
		fireStation2.setAddress("address1");
		fireStation2.setStation("102");
		//
		fireStationDao.deleteFireStationAddress(fireStation2);
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationDao.getAll().size());
	}
	
	@Test
	void deleteFireStationStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
		FireStationDao fireStationDao = new FireStationDaoImpl();
		int nbItem1 = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		int nbItem2 = fireStationDao.getAll().size();
		//
		FireStation fireStation2 = new FireStation();
		fireStation2.setAddress("address2");
		fireStation2.setStation("100");
		//
		fireStationDao.deleteFireStationStation(fireStation2);
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationDao.getAll().size());
	}

}
