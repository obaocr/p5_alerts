package com.safetynet.p5_alerts.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

import ch.qos.logback.classic.Logger;

@Service
public class PersonServiceImpl implements PersonService {

	Logger log = (Logger) LoggerFactory.getLogger(MainServiceImpl.class);
	
	@Autowired
	private PersonDao personDao;

	@Override
	public List<Person> getPersons() {
		log.debug("PersonServiceImpl : getPersons");
		return personDao.getAll();
	}

	public boolean addPerson(Person person) {
		log.debug("PersonServiceImpl : addPerson");
		return personDao.addPerson(person);
	}

	@Override
	public boolean updatePerson(Person person) {
		log.debug("PersonServiceImpl : updatePerson");
		return personDao.updatePerson(person);
	}

	@Override
	public boolean deletePerson(PersonForAPIDelete person) {
		log.debug("PersonServiceImpl : deletePerson");
		return personDao.deletePerson(person);
	}
}
