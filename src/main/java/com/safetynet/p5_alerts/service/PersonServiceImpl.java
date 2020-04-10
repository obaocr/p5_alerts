package com.safetynet.p5_alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;
/**
 * 
 * PersonService implementation
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger log = LogManager.getLogger(PersonServiceImpl.class);

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
	public List<Person> deletePerson(PersonForAPIDelete person) {
		log.debug("PersonServiceImpl : deletePerson");
		return personDao.deletePerson(person);
	}
}
