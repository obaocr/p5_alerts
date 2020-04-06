package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.HouseholdResponse;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;
import com.safetynet.p5_alerts.service.FloodStationService;
import com.safetynet.p5_alerts.service.MainService;
import com.safetynet.p5_alerts.util.EntityIllegalArgumentException;

/**
 * Controller for API Business
 */
@RestController
public class MainController {
	private static final Logger log = LogManager.getLogger(MainController.class);

	@Autowired
	private MainService mainService;

	@Autowired
	private FloodStationService floodStationService;

	@GetMapping(value = "communityEmail")
	public CommunityEmail getCommunityEmails(@RequestParam String city) {
		log.debug("communityEmail : String city is empty");
		if (city.isEmpty()) {
			log.error("communityEmail : list persons emails for a city");
			throw new EntityIllegalArgumentException("The parameter city must be set");
		}
		return mainService.getCommunityEmails(city);
	}

	@GetMapping(value = "personInfo")
	public PersonInfoResponse personInfo(@RequestParam String lastName, String firstName) {
		if (lastName.isEmpty() || firstName.isEmpty()) {
			throw new EntityIllegalArgumentException("The parameters lastName and firstName must be set");
		}
		log.debug("personInfo : infos persons for a 'name'");
		return mainService.personInfo(lastName, firstName);
	}

	@GetMapping(value = "childAlert")
	public ChildAlertResponse childAlert(@RequestParam String address) {
		log.debug("childAlert : list of children for an address");
		if (address.isEmpty()) {
			throw new EntityIllegalArgumentException("The parameter address must be set");
		}
		return mainService.childAlert(address);
	}

	@GetMapping(value = "firestation")
	public FirestationPerson firestationPerson(@RequestParam String stationNumber) {
		log.debug("firestationPerson : list of person for firestation address");
		if (stationNumber.isEmpty()) {
			throw new EntityIllegalArgumentException("The parameter stationNumber must be set");
		}
		return mainService.firestation(stationNumber);
	}

	@GetMapping(value = "phoneAlert")
	public PhoneAlert phoneAlert(@RequestParam String stationNumber) {
		log.info("phoneAlert : list of phone person for firestation number");
		if (stationNumber.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return mainService.phoneAlert(stationNumber);
	}

	@GetMapping(value = "fire")
	public PersonForFirestationAddressResponse fire(@RequestParam String address) {
		log.info("fire : Persons infos for a firestation address");
		if (address.isEmpty()) {
			throw new EntityIllegalArgumentException("The parameter address must be set");
		}
		return mainService.fire(address);
	}

	@GetMapping(value = "flood/stations")
	public HouseholdResponse floodStations(@RequestParam List<String> stations) {
		if (stations.isEmpty()) {
			throw new EntityIllegalArgumentException("The parameter stations must be set");
		}
		log.info("floodStations : households for stations addresses");
		return floodStationService.floodStations(stations);
	}

}
