package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.service.MainService;
import com.safetynet.p5_alerts.service.PersonService;

import ch.qos.logback.classic.Logger;

@RestController
public class MainController {
	Logger log = (Logger) LoggerFactory.getLogger(MainController.class);

	@Autowired
	private MainService mainService;

	@Autowired
	private PersonDao personDao;

	@GetMapping(value = "communityEmail")
	public List<String> getCommunityEmails(@RequestParam String city) {
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
	public FirestationPerson firestationPerson(@RequestParam String station) {
		log.info("firestationPerson : list of person for firestation address");
		return mainService.firestation(station);
	}

}
