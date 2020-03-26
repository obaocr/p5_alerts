package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.util.List;

import com.safetynet.p5_alerts.model.Person;

public interface PersonService {

	/**
	 * 
	 * @throws IOException
	 */
	public List<Person> getPersons();

	public List<String> getCommunityEmails(String city);

	public boolean addPerson(Person person);

	public boolean updatePerson(Person person);

	public boolean deletePerson(Person person);
}
