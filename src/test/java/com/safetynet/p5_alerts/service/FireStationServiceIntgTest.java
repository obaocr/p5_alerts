package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.FireStationDaoImpl;
import com.safetynet.p5_alerts.model.FireStation;

@ContextConfiguration
@SpringBootTest
class FireStationServiceIntgTest {

	@Autowired
	private FireStationService fireStationService;

	@Test
	void getAllTest() {
		int nbItem = fireStationService.getFireStations().size();
		System.out.println("FireStationServiceTest getAllTest;"+ fireStationService.getFireStations().size());
		assertTrue(nbItem > 1);
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
		FireStationDao fireStationDao = new FireStationDaoImpl();
		fireStationDao.addFireStation(fireStation);
		// Mise a jour de la station avec vakeur finale
		fireStation.setStation(stationValue);
		fireStationService.updateFireStation(fireStation);
		// Recrcheche de la fireStation mise a jour
		List<FireStation> fireStationMaj = fireStationService.getFireStations();
		for (FireStation fs : fireStationMaj) {
			if (fs.getAddress().equals(addressKey) && fs.getStation().equals(stationValue)) {
				isUpdated = true;
			}
		}
		assertTrue(isUpdated == true);
	}

	@Test
	void deleteFireStationAddressStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address-deleteFireStationbyAddress");
		fireStation.setStation("674");
		int nbItem1 = fireStationService.getFireStations().size();
		fireStationService.addFireStation(fireStation);
		int nbItem2 = fireStationService.getFireStations().size();
		//
		fireStationService.deleteFireStationbyAddress("address-deleteFireStationbyAddress");
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationService.getFireStations().size());
	}

	@Test
	void deleteFireStationStationTest() {
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address10");
		fireStation.setStation("100");
		int nbItem1 = fireStationService.getFireStations().size();
		fireStationService.addFireStation(fireStation);
		int nbItem2 = fireStationService.getFireStations().size();
		//
		fireStationService.deleteFireStationbyStation("100");
		assertTrue(nbItem2 == nbItem1 + 1);
		assertTrue(nbItem1 == fireStationService.getFireStations().size());
	}

}
