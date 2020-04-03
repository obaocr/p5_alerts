package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

@SpringBootTest
class FireStationDaoTest {

	@Autowired
	private DataService dataService;

	@Autowired
	FireStationDao fireStationDao;
	
	@BeforeEach
	private void initData() throws IOException {
		// Before Each car le Before All est exécuté avant le conetxte spring et donc avant le commandLineRunner
		fireStationDao.deleteAll();
		dataService.loadData();
	}

	@Test
	void getAllTest() {
		int nbItem = fireStationDao.getAll().size();
		assertTrue(nbItem > 1);
	}

	@Test
	void addFireStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address");
		fireStation.setStation("100");
		int nbItem = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		assertTrue(nbItem + 1 == fireStationDao.getAll().size());

	}
	
	@Test
	void updateFireStationTest() {
		String addressKey = "addressKey";
		String stationValue = "999";
		boolean isUpdated = false;
		// Ajout station
		FireStation fireStation = new FireStation();
		fireStation.setAddress(addressKey);
		fireStation.setStation("888");
		fireStationDao.addFireStation(fireStation);
		// Mise a jour de la station avec vakeur finale
		fireStation.setStation(stationValue);
		fireStationDao.updateFireStation(fireStation);
		//Recrcheche de la fireStation mise a jour
		List<FireStation> fireStationMaj = fireStationDao.getAll();
		for (FireStation fs : fireStationMaj) {
			if(fs.getAddress().equals(addressKey) && fs.getStation().equals(stationValue)) {
				isUpdated = true;
			}
		}
		assertTrue(isUpdated == true);
	}
	
	@Test
	void deleteFireStationAddressStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("100");
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
		fireStation.setAddress("address10");
		fireStation.setStation("100");
		int nbItem1 = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		int nbItem2 = fireStationDao.getAll().size();
		//
		FireStation fireStation2 = new FireStation();
		fireStation2.setAddress("address20");
		fireStation2.setStation("100");
		//
		fireStationDao.deleteFireStationStation(fireStation2);
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationDao.getAll().size());
	}
	
	@Test
	void searchByAddressTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("125 rue des tulipiers");
		fireStation.setStation("127585");
		fireStationDao.addFireStation(fireStation);
		assertTrue(fireStationDao.searchByAddress("125 rue des tulipiers").getStation().equals("127585"));

	}
	
	@Test
	void searchByStationTest() {
		FireStation fireStation;
		fireStation = new FireStation();
		fireStation.setAddress("address1");
		fireStation.setStation("625");
		fireStationDao.addFireStation(fireStation);
		fireStation = new FireStation();
		fireStationDao.addFireStation(fireStation);
		fireStation.setAddress("address1");
		fireStation.setStation("625");
		fireStationDao.addFireStation(fireStation);
		fireStation = new FireStation();
		fireStationDao.addFireStation(fireStation);
		fireStation.setAddress("address2");
		fireStation.setStation("625");
		fireStationDao.addFireStation(fireStation);
		List<String> laddress = fireStationDao.searchByStation("625");
		// 2 addresses car de doublonnées
		assertTrue(laddress.size() == 2);

	}

}
