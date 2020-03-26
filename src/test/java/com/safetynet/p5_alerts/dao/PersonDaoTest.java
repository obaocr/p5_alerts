package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

class PersonDaoTest {

	@BeforeAll
	private static void initData() {
		PersonDao personDao = new PersonDaoImpl();
		personDao.deleteAll();
		DataService ds = new DataServiceImpl("data_test.json");
		ds.loadData();
	}

	@Test
	void getAllTest() {
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons = personDao.getAll().size();
		assertTrue(nbPersons > 0);
	}

	@Test
	// Test de la recherche des emails des personnes d'une ville inexistante
	void communityEmailEmptyTest() {
		PersonDao personDao = new PersonDaoImpl();
		List<String> emails = personDao.getCommunityEmails("Culver");
		assertTrue(emails.size() > 0);
	}

	@Test
	// Test ajout de personne
	void addPersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons = personDao.getAll().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		assertTrue(nbPersons + 1 == personDao.getAll().size());

	}

	@Test
	// Suppression de personne
	void deletePersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons1 = personDao.getAll().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		personDao.deletePerson(p);
		assertTrue(nbPersons2 == nbPersons1+1);
		assertTrue(nbPersons1 == personDao.getAll().size());

	}

}
