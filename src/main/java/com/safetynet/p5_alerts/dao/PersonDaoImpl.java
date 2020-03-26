package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
	
	public PersonDaoImpl () {
		
	}
	
	public void setPersons (List<Person> persons) {
		personData = persons;
	}
	
	// Retourne toutes les personnes
	@Override
	public List<Person> getAll() {
		log.info("getAll : list of persons");
		return personData;
	}

	// Retourne une liste d'emails pour les personnes habitants dans une ville
	@Override
	public List<String> getCommunityEmails(String city) {
		log.info(" PersonDao communityEmail : list persons emails for a city");
		List<String> emails = new ArrayList<>();
		System.out.println("Dans PersonDaoImpl.communityEmaiTtes, persons.size : " + personData.size());
		for (Person person : personData) {
			// !!! mettre equals pour tester la valeur sinon KO !!!
			if (person.getCity().equals(city)) {
				emails.add(person.getEmail());
			}
		}
		return emails;
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
		public boolean deletePerson(Person person) {
			boolean isFound = false;
			log.info("dPersonDao eletePerson : delete a person");
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
		
		// Suppression de toutes les personnes
		@Override
		public boolean deleteAll() {
			log.info("PersonDao deleteAll : delete all person");
			if(personData != null) {personData.clear();};
			return true;
		}
}
