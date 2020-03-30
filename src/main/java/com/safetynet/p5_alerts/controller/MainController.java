package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.Household;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.model.PhoneAlert;
import com.safetynet.p5_alerts.service.FloodStationService;
import com.safetynet.p5_alerts.service.MainService;

import ch.qos.logback.classic.Logger;

@RestController
public class MainController {
	Logger log = (Logger) LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService mainService;

	@Autowired
	private FloodStationService floodStationService;
	
	@Autowired
	private PersonDao personDao;

	@GetMapping(value = "communityEmail")
	public CommunityEmail getCommunityEmails(@RequestParam String city) {
		log.info("communityEmail : list persons emails for a city");
		return personDao.getCommunityEmails(city);
	}

	@GetMapping(value = "personInfo")
	public List<PersonInfo> personInfo(@RequestParam String lastName, String firstName) {
		log.info("personInfo : infos persons for a 'name'");
		return mainService.personInfo(lastName, firstName);
	}

	@GetMapping(value = "childAlert")
	public List<ChildAlert> childAlert(@RequestParam String address) {
		log.info("childAlert : list of children for an address");
		return mainService.childAlert(address);
	}

	@GetMapping(value = "firestation")
	public FirestationPerson firestationPerson(@RequestParam String stationNumber) {
		log.info("firestationPerson : list of person for firestation address");
		return mainService.firestation(stationNumber);
	}
	
	@GetMapping(value = "phoneAlert")
	public PhoneAlert phoneAlert(@RequestParam String stationNumber) {
		log.info("phoneAlert : list of phone person for firestation number");
		return mainService.phoneAlert(stationNumber);
	}
	
	@GetMapping(value = "fire")
	public List<PersonForFirestationAddress> fire(@RequestParam String address) {
		log.info("fire : Persons infos for a firestation address");
		return mainService.fire(address);
	}
	
	@GetMapping(value = "flood/stations")
	public List<Household> floodStations(@RequestParam List<String> stations) {
		log.info("floodStations : households for stations addresses");
		return floodStationService.floodStations(stations);
	}

}
