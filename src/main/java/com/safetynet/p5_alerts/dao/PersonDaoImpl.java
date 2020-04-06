package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * PersonDao implementation
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	private static final Logger log = LogManager.getLogger(PersonDaoImpl.class);
	private static List<Person> personData = new ArrayList<>();;

	public PersonDaoImpl() {
	}

	public PersonDaoImpl(List<Person> persons) {
		if (personData != null) {
			personData.clear();
		}
		for (Person person : persons) {
			personData.add(person);
		}
	}

	// Retourne toutes les personnes
	@Override
	public List<Person> getAll() {
		log.debug("getAll : list of persons");
		return personData;
	}

	// Recherche par prenom, nom
	@Override
	public Person searchByName(String firstName, String lastName) {
		log.debug("PersonDao searchByName");
		for (Person person : personData) {
			if (person.getFirstname().equals(firstName) && person.getLastname().equals(lastName)) {
				return person;
			}
		}
		return null;
	}

	// Recherche par adresse
	@Override
	public List<Person> searchByAddress(String address) {
		log.debug("PersonDao searchByAddress");
		List<Person> persons = new ArrayList<>();
		for (Person person : personData) {
			if (person.getAddress().equals(address)) {
				persons.add(person);
			}
		}
		return persons;
	}

	// Retourne une liste d'emails pour les personnes habitants dans une ville
	// On dedoublonne
	@Override
	public CommunityEmail getCommunityEmails(String city) {
		log.debug(" PersonDao communityEmail : list persons emails for a city");
		CommunityEmail communityEmail = new CommunityEmail();
		Set<String> setEmails = new HashSet<>();
		for (Person person : personData) {
			// !!! mettre equals pour tester la valeur sinon KO !!!
			if (person.getCity().equals(city)) {
				setEmails.add(person.getEmail());
			}
		}
		List<String> emails = setEmails.stream().collect(Collectors.toList());
		communityEmail.setEmails(emails);
		return communityEmail;
	}

	// Ajout personne
	@Override
	public boolean addPerson(Person person) {
		log.debug("PersonDao addPerson : Add a person");
		personData.add(person);
		return true;
	}

	// Mise a jour personne
	@Override
	public boolean updatePerson(Person person) {
		log.debug("PersonDao updatePerson : update a person");
		int pos = 0;
		boolean isFound = false;
		for (Person p : personData) {
			if (p.getFirstname().equals(person.getFirstname()) && p.getLastname().equals(person.getLastname())) {
				personData.set(pos, person);
				isFound = true;
			}
			pos++;
		}
		return isFound;
	}

	// Suppression personne
	@Override
	public boolean deletePerson(PersonForAPIDelete person) {
		boolean isFound = false;
		log.debug("dPersonDao eletePerson : delete a person");
		Iterator<Person> i = personData.iterator();
		while (i.hasNext()) {
			Person o = i.next();
			if (o.getFirstname().equals(person.getFirstname()) && o.getLastname().equals(person.getLastname())) {
				i.remove();
				isFound = true;
			}
		}
		return isFound;
	}

}
