package com.safetynet.p5_alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<Person> getPersons() {
		return personDao.getAll();
	}

	@Override
	public List<String> getCommunityEmails(String city) {
		return personDao.getCommunityEmails(city);
	}

	public boolean addPerson(Person person) {
		return personDao.addPerson(person);
	}

	@Override
	public boolean updatePerson(Person person) {
		return personDao.updatePerson(person);
	}

	@Override
	public boolean deletePerson(Person person) {
		return personDao.deletePerson(person);
	}
}
