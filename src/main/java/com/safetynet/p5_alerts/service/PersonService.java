package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * Interface for PersonService
 */
public interface PersonService {

	public List<Person> getPersons();

	public boolean addPerson(Person person);

	public boolean updatePerson(Person person);

	public boolean deletePerson(PersonForAPIDelete person);
}
