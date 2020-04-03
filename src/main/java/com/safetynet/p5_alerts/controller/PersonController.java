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

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;
import com.safetynet.p5_alerts.service.PersonService;
import com.safetynet.p5_alerts.util.EntityIllegalArgumentException;

import ch.qos.logback.classic.Logger;

/**
 * Controller for Person object
 */
@RestController
public class PersonController {

	Logger log = (Logger) LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonService personService;

	@GetMapping(value = "person/all")
	public List<Person> all() {
		log.debug("persons/all : list of persons");
		return personService.getPersons();
	}
	
	private void checkInputPerson(Person person) {
		if(person == null || person.getFirstname().isEmpty() || person.getLastname().isEmpty()) {
			throw new EntityIllegalArgumentException("Firstname and Lastname are mandatory");
		}
	}
	
	private void checkInputPersonForAPIDelete(PersonForAPIDelete person) {
		if(person == null || person.getFirstname().isEmpty() || person.getLastname().isEmpty()) {
			throw new EntityIllegalArgumentException("Firstname and Lastname are mandatory");
		}
	}
	
	@PostMapping(value = "person")
	public boolean addPerson(@RequestBody Person person) {
		log.debug("Create a person");
		checkInputPerson(person);
		return personService.addPerson(person);
	}

	@PutMapping(value = "person")
	public boolean updatePerson(@RequestBody Person person) {
		log.debug("Update a person");
		checkInputPerson(person);
		return personService.updatePerson(person);
	}

	@DeleteMapping(value = "person")
	public boolean deletePerson(@RequestBody PersonForAPIDelete person) {
		log.debug("Detete a person");
		checkInputPersonForAPIDelete(person);
		return personService.deletePerson(person);
	}

}
