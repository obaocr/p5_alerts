package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.model.CommunityEmail;
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
	void searchByNameTest() {
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons = personDao.getAll().size();
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Maria").setLastname("Martinez").setAddress("address").setCity("Versailles")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		Person ps = personDao.searchByName("Maria","Martinez");
		assertTrue(nbPersons + 1  == nbPersons2);
		assertTrue(ps.getCity().equals("Versailles") == true);
	}
	
	@Test
	void searchByAddressTest() {
		PersonDao personDao = new PersonDaoImpl();
		int nbPersons = personDao.getAll().size();
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Alex").setLastname("Martinez").setAddress("15 rue des olivier").setCity("Montelimar")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		List<Person> persons = personDao.searchByAddress("15 rue des olivier");
		assertTrue(nbPersons + 1  == nbPersons2);
		assertTrue(persons.size() > 0);
	}

	@Test
	// Test de la recherche des emails des personnes d'une ville inexistante
	void communityEmailEmptyTest() {
		PersonDao personDao = new PersonDaoImpl();
		CommunityEmail communityEmail;
		communityEmail = personDao.getCommunityEmails("Culver");
		assertTrue(communityEmail.getEmails().size() > 0);
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
	// Test mise a jour personne
	void updatePersonTest() {
		boolean isUpdated = false;
		// on ajoute une personne
		Person.Builder personlBuilder = new Person.Builder();
		PersonDao personDao = new PersonDaoImpl();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		// On met a jour cette personne
		p.setCity("Paris");
		personDao.updatePerson(p);
		// On recherche cette personne
		List<Person> persons = personDao.getAll();
		for(Person psn : persons) {
			if (psn.getFirstname().equals("Olivier") && psn.getLastname().equals("Martin") && psn.getCity().equals("Paris")) {
				isUpdated = true;
			}
		}
		assertTrue(isUpdated == true);

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
