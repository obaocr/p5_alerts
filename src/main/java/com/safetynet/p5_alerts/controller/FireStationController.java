package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.FireStationService;

import ch.qos.logback.classic.Logger;

@RestController
public class FireStationController {
	Logger log = (Logger) LoggerFactory.getLogger(FireStationController.class);

	@Autowired
	private FireStationService fireStationService;

	@GetMapping(value = "firestation/all")
	public List<FireStation> all() {
		log.debug("persons/all : list of persons");
		return fireStationService.getFireStations();
	}

	@PostMapping(value = "firestation")
	public boolean addFireStation(@RequestBody FireStation fireStation) {
		log.debug("Create a firestation");
		return fireStationService.addFireStation(fireStation);
	}

	@PutMapping(value = "firestation")
	public boolean updateFireStation(@RequestBody FireStation fireStation) {
		log.debug("Update a firestation by address");
		return fireStationService.updateFireStation(fireStation);
	}

	@DeleteMapping(value = "firestation")
	public List<FireStation> deleteFireStationByStation(@RequestBody FireStation fireStation) {
		log.debug("Detete a firestation");
		if(fireStation == null || (fireStation.getStation().isEmpty() && fireStation.getAddress().isEmpty())) {
			throw new IllegalArgumentException();
		}
		if (!fireStation.getAddress().isEmpty()) {
			return fireStationService.deleteFireStationbyAddress(fireStation);
		} else {
			if (!fireStation.getStation().isEmpty()) {
				return fireStationService.deleteFireStationbyStation(fireStation);
			}
		}
		return null;
	}

}
