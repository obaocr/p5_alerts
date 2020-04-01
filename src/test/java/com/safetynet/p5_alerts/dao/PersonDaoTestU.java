package com.safetynet.p5_alerts.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

// Unit tests / no input data
class PersonDaoTestU {
	
	PersonDao personDao;
	List<Person> persons;
	
	@BeforeEach
	private void init() {
		personDao = new PersonDaoImpl();
		persons = new ArrayList<>();
		personDao.setPersons(persons);
	}
	
	@Test
	void getAllTest() {
		Person person = new Person();
		person.setFirstname("Maria");
		person.setLastname("Martinez");
		person.setAddress("address");
		person.setCity("Versailles");
		person.setZip("zip");
		person.setPhone("phone");
		person.setEmail("email");
		personDao.addPerson(person);
		int nbPersons = personDao.getAll().size();
		assertTrue(nbPersons == 1);
	}

	@Test
	void searchByNameTest() {
		int nbPersons = personDao.getAll().size();
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Maria").setLastname("Martinez").setAddress("address")
				.setCity("Versailles").setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		Person ps = personDao.searchByName("Maria", "Martinez");
		assertTrue(nbPersons + 1 == nbPersons2);
		assertTrue(ps.getCity().equals("Versailles") == true);
	}

	@Test
	void searchByAddressTest() {
		int nbPersons = personDao.getAll().size();
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Alex").setLastname("Martinez").setAddress("15 rue des olivier")
				.setCity("Montelimar").setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		persons = personDao.searchByAddress("15 rue des olivier");
		assertTrue(nbPersons + 1 == nbPersons2);
		assertTrue(persons.size() > 0);
	}

	@Test
	// Test de la recherche des emails des personnes d'une ville inexistante
	void communityEmailEmptyTest() {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Alex").setLastname("Martinez").setAddress("15 rue des olivier")
				.setCity("Montelimar").setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		CommunityEmail communityEmail;
		communityEmail = personDao.getCommunityEmails("Montelimar");
		assertTrue(communityEmail.getEmails().size() == 1);
	}

	@Test
	// Test ajout de personne
	void addPersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
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
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		// On met a jour cette personne
		p.setCity("Paris");
		personDao.updatePerson(p);
		// On recherche cette personne
		persons = personDao.getAll();
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
		PersonForAPIDelete personForAPIDelete = new PersonForAPIDelete();
		personForAPIDelete.setFirstname("Olivier");
		personForAPIDelete.setLastname("Martin");
		Person.Builder personlBuilder = new Person.Builder();
		int nbPersons1 = personDao.getAll().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address").setCity("city")
				.setZip("zip").setPhone("phone").setEmail("email").build();
		personDao.addPerson(p);
		int nbPersons2 = personDao.getAll().size();
		personDao.deletePerson(personForAPIDelete);
		assertTrue(nbPersons2 == nbPersons1 + 1);
		assertTrue(nbPersons1 == personDao.getAll().size());
	}

}
