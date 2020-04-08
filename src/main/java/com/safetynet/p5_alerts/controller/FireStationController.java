package com.safetynet.p5_alerts.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.DeleteResponseController;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.service.FireStationService;
import com.safetynet.p5_alerts.util.EntityIllegalArgumentException;

/**
 * Controller for FireStation object
 */
@RestController
public class FireStationController {

	private static final Logger log = LogManager.getLogger(FireStationController.class);

	@Autowired
	private FireStationService fireStationService;

	@GetMapping(value = "firestation/all")
	public List<FireStation> all() {
		log.debug("persons/all : list of persons");
		return fireStationService.getFireStations();
	}

	private void checkInput(FireStation fireStation) {
		if (fireStation == null || fireStation.getAddress().isEmpty() || fireStation.getStation().isEmpty()) {
			log.error("Address and Station are mandatory");
			throw new EntityIllegalArgumentException("Address and Station are mandatory");
		}
	}

	@PostMapping(value = "firestation")
	public boolean addFireStation(@RequestBody FireStation fireStation) {
		log.debug("Create a firestation");
		checkInput(fireStation);
		return fireStationService.addFireStation(fireStation);
	}

	@PutMapping(value = "firestation")
	public boolean updateFireStation(@RequestBody FireStation fireStation) {
		log.debug("Update a firestation by address");
		checkInput(fireStation);
		return fireStationService.updateFireStation(fireStation);
	}

	@DeleteMapping(value = "firestation")
	public DeleteResponseController deleteFireStation(@RequestBody FireStation fireStation) {
		log.debug("Detete a firestation");
		List<FireStation> fireStations = new ArrayList<>();
		if (fireStation == null || (fireStation.getAddress().isEmpty() && fireStation.getStation().isEmpty())) {
			log.error("Address OR Station are mandatory");
			throw new EntityIllegalArgumentException("Address OR Station are mandatory");
		}
		if (!fireStation.getAddress().isEmpty()) {
			fireStations =  fireStationService.deleteFireStationbyAddress(fireStation.getAddress());
		} else {
			if (!fireStation.getStation().isEmpty()) {
				fireStations =  fireStationService.deleteFireStationbyStation(fireStation.getStation());
			}
		}
		DeleteResponseController deleteResponseController = new DeleteResponseController();
		deleteResponseController.setNbOfItemDeleted(fireStations.size());
		return deleteResponseController;
	}

}
