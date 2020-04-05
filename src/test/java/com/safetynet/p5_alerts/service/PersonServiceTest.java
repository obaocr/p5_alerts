package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

@ContextConfiguration
@SpringBootTest
class PersonServiceTest {

	@Autowired
	private PersonService personService;

	@Test
	void getAllTest() {
		List<Person> persons = personService.getPersons();
		int nbPersons = persons.size();
		assertTrue(nbPersons > 0);
	}

	@Test
	// Test ajout de personne
	void addPersonTest() {
		Person.Builder personlBuilder = new Person.Builder();
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
		PersonForAPIDelete personForAPIDelete = new PersonForAPIDelete();
		personForAPIDelete.setFirstname("Olivier");
		personForAPIDelete.setLastname("PersonServiceTest.deletePersonTest");
		Person.Builder personlBuilder = new Person.Builder();
		int nbPersons1 = personService.getPersons().size();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("PersonServiceTest.deletePersonTest")
				.setAddress("address").setCity("city").setZip("zip").setPhone("phone").setEmail("email").build();
		personService.addPerson(p);
		int nbPersons2 = personService.getPersons().size();
		personService.deletePerson(personForAPIDelete);
		assertTrue(nbPersons2 == nbPersons1 + 1);
		int nbPersons3 = personService.getPersons().size();
		assertTrue(nbPersons1 == nbPersons3);
	}

}
