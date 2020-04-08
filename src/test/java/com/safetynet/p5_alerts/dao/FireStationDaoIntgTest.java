package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.DataService;

@SpringBootTest
class FireStationDaoIntgTest {

	@Autowired
	private DataService dataService;

	@Autowired
	FireStationDao fireStationDao;

	@BeforeEach
	private void initData() throws IOException {
		dataService.loadData();
	}

	@Test
	void getAllTest() {
		int nbItem = fireStationDao.getAll().size();
		assertTrue(nbItem > 1);
	}

	@Test
	void addFireStationTest() {
		FireStation fireStation = new FireStation("100", "address");
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
		// Recrcheche de la fireStation mise a jour
		List<FireStation> fireStationMaj = fireStationDao.getAll();
		for (FireStation fs : fireStationMaj) {
			if (fs.getAddress().equals(addressKey) && fs.getStation().equals(stationValue)) {
				isUpdated = true;
			}
		}
		assertTrue(isUpdated == true);
	}

	@Test
	void deleteFireStationAddressStationTest() {
		FireStation fireStation = new FireStation("100", "address1");
		int nbItem1 = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		int nbItem2 = fireStationDao.getAll().size();
		//
		List<FireStation> fireStationResponse = fireStationDao.deleteFireStationAddress("address1");
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationDao.getAll().size());
		assertTrue(fireStationResponse.size() == 1);
	}

	@Test
	void deleteFireStationStationTest() {
		FireStation fireStation = new FireStation("100", "address10");
		int nbItem1 = fireStationDao.getAll().size();
		fireStationDao.addFireStation(fireStation);
		int nbItem2 = fireStationDao.getAll().size();
		//
		List<FireStation> fireStationResponse = fireStationDao.deleteFireStationStation("100");
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationDao.getAll().size());
		assertTrue(fireStationResponse.size() == 1);
	}

	@Test
	void searchByAddressTest() {
		FireStation fireStation = new FireStation("127585", "125 rue des tulipiers");
		fireStationDao.addFireStation(fireStation);
		assertTrue(fireStationDao.searchByAddress("125 rue des tulipiers").getStation().equals("127585"));

	}

	@Test
	void searchByStationTest() {
		FireStation fireStation;
		fireStation = new FireStation("625", "address1");
		fireStationDao.addFireStation(fireStation);
		fireStation = new FireStation();
		fireStationDao.addFireStation(fireStation);
		fireStation.setAddress("address1");
		fireStation.setStation("625");
		fireStationDao.addFireStation(fireStation);
		fireStation = new FireStation("625", "address2");
		fireStationDao.addFireStation(fireStation);
		fireStationDao.addFireStation(fireStation);
		List<String> laddress = fireStationDao.searchByStation("625");
		// 2 addresses car de doublonnées
		assertTrue(laddress.size() == 2);

	}

}
