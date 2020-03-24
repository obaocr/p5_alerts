package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.Person;


@RestController
public class PersonController {
	
	@Autowired
	private  PersonDao personDao;
	
	@GetMapping(value="communityEmail")
	public List<String> communityEmail(@RequestParam String city) {
		return personDao.communityEmail(city);
	}
	
	@GetMapping(value="persons/findAll")
	public List<Person> findAll() {
		return personDao.getAll();
	}
	
	@PostMapping(value="person")
	public boolean addPerson(@RequestBody Person person) {
		personDao.addPerson(person);
		return true;
	}
	
	@PutMapping(value="person")
	public boolean updatePerson(@RequestBody Person person) {
		personDao.updatePerson(person);
		return true;
	}
	
	@DeleteMapping(value="person")
	public boolean deletePerson(@RequestBody Person person) {
		personDao.deletePerson(person);
		return true;
	}

}
