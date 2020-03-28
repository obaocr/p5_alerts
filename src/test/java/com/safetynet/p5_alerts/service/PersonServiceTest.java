package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;
import com.safetynet.p5_alerts.model.Person;

class PersonServiceTest {


	@BeforeAll
	private static void initData() {
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
		PersonDao personDao = new PersonDaoImpl();
		System.out.println("Test PersonService 1 taille liste personne : " + personDao.getAll().size());
	}
	
	
	@Test
	void
	getAllTest() {
		PersonService personService = new PersonServiceImpl();
		List<Person> persons = personService.getPersons();
		int nbPersons = personService.getPersons().size();
		assertTrue(nbPersons > 0);
	}

	/*
	@Test
	// Test de la recherche des emails des personnes d'une ville inexistante
	void communityEmailEmptyTest() {
		PersonService personService = new PersonServiceImpl();
		List<String> emails = personService.getCommunityEmails("Culver");
		assertTrue(emails.size() > 0);
	}

	@Test
	// Test ajout de personne
	void addPersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
		PersonService personService = new PersonServiceImpl();
		int nbPersons = personService.getPersons().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		assertTrue(nbPersons + 1 == personService.getPersons().size());

	}

	@Test
	// Test mise a jour personne
	void updatePersonTest() {
		boolean isUpdated = false;
		// on ajoute une personne
		Person.Builder personlBuilder = new Person.Builder();
		PersonService personService = new PersonServiceImpl();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		// On met a jour cette personne
		p.setCity("Paris");
		personService.updatePerson(p);
		// On recherche cette personne
		List<Person> persons = personService.getPersons();
		for (Person psn : persons) {
			if (psn.getFirstname().equals("Olivier") && psn.getLastname().equals("Martin")
					&& psn.getCity().equals("Paris")) {
				isUpdated = true;
			}
		}
		assertTrue(isUpdated == true);

	}

	@Test
	// Suppression de personne
	void deletePersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
		PersonService personService = new PersonServiceImpl();
		int nbPersons1 = personService.getPersons().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		int nbPersons2 = personService.getPersons().size();
		personService.deletePerson(p);
		assertTrue(nbPersons2 == nbPersons1 + 1);
		assertTrue(nbPersons1 == personService.getPersons().size());
	]
	*/

}
