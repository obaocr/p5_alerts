package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.Person;

import ch.qos.logback.classic.Logger;

/**
 * @author S063912
 *
 */
@Repository
public class PersonDaoImpl implements PersonDao {

	private static List<Person> personData;

	Logger log = (Logger) LoggerFactory.getLogger(PersonDaoImpl.class);

	public PersonDaoImpl() {

	}

	public void setPersons(List<Person> persons) {
		personData = persons;
	}

	// Retourne toutes les personnes
	@Override
	public List<Person> getAll() {
		log.info("getAll : list of persons");
		return personData;
	}

	// Recherche par prenom, nom
	@Override
	public Person searchByName(String firstName, String lastName) {
		log.info("PersonDao searchByName");
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
			log.info("PersonDao searchByAddress");
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
		log.info(" PersonDao communityEmail : list persons emails for a city");
		CommunityEmail communityEmail = new CommunityEmail();
		Set<String> setEmails = new HashSet<>();
		List<String> emails = new ArrayList<>();
		System.out.println("Dans PersonDaoImpl.communityEmaiTtes, persons.size : " + personData.size());
		for (Person person : personData) {
			// !!! mettre equals pour tester la valeur sinon KO !!!
			if (person.getCity().equals(city)) {
				setEmails.add(person.getEmail());
			}
		}
		if(setEmails.size() > 0) {
			emails = setEmails.stream().collect(Collectors.toList());
			communityEmail.setEmails(emails);
		}
		return communityEmail;
	}

	// Ajout personne
	@Override
	public boolean addPerson(Person person) {
		log.info("PersonDao addPerson : Add a person");
		personData.add(person);
		return true;
	}

	// Mise a jour personne
	@Override
	public boolean updatePerson(Person person) {
		log.info("PersonDao updatePerson : update a person");
		int pos = 0;
		boolean isFound = false;
		for (Person p : personData) {
			if (p.getFirstname().equals(person.getFirstname().toString())
					&& p.getLastname().equals(person.getLastname().toString())) {
				personData.set(pos, person);
				isFound = true;
			}
			pos++;
		}
		return isFound;
	}

	// Suppression personne
	@Override
	public boolean deletePerson(Person person) {
		boolean isFound = false;
		log.info("dPersonDao eletePerson : delete a person");
		Iterator<Person> i = personData.iterator();
		while (i.hasNext()) {
			Person o = i.next();
			if (o.getFirstname().equals(person.getFirstname().toString())
					&& o.getLastname().equals(person.getLastname().toString())) {
				i.remove();
				isFound = true;
			}
		}
		return isFound;
	}

	// Suppression de toutes les personnes
	@Override
	public boolean deleteAll() {
		log.info("PersonDao deleteAll : delete all person");
		if (personData != null) {
			personData.clear();
		}
		;
		return true;
	}
}
