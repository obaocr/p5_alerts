package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * Interface for PersonDao
 */
public interface PersonDao {
	public List<Person> getAll();
	public Person searchByName(String lastName, String firstName);
	public List<Person> searchByAddress(String address);
	public CommunityEmail getCommunityEmails(String city);
	public boolean addPerson(Person person);
	public boolean updatePerson(Person person);
	public List<Person> deletePerson(PersonForAPIDelete person);
}
