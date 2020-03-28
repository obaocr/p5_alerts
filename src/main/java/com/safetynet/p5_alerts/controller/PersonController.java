package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.service.PersonService;

import ch.qos.logback.classic.Logger;


@RestController
public class PersonController {
	
	Logger log = (Logger) LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private  PersonService personService;
	
	@GetMapping(value="person/all")
	public List<Person> all() {
		log.info("persons/all : list of persons");
		return personService.getPersons();
	}
	
	@PostMapping(value="person")
	public boolean addPerson(@RequestBody Person person) {
		log.info("Create a person");
		return personService.addPerson(person);
	}
	
	@PutMapping(value="person")
	public boolean updatePerson(@RequestBody Person person) {
		log.info("Update a person");
		return personService.updatePerson(person);
	}
	
	@DeleteMapping(value="person")
	public boolean deletePerson(@RequestBody Person person) {
		log.info("Detete a person");
		return personService.deletePerson(person);
	}
}
