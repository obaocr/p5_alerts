package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.HouseholdResponse;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.util.EntityNotFoundException;

@ContextConfiguration
@SpringBootTest
class MainServiceIntgTest {

	@Autowired
	private MainService mainService;

	@Autowired
	private PersonService personService;

	@Autowired
	private DataService dataService;

	@BeforeEach
	private void initData() throws IOException {
		dataService.loadData();
	}

	@Test
	void getCommunityEmailsTest() {
		//
		// Ajout psn
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Olivier").setLastname("Martin").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email1@getCommunityEmails.fr")
				.build();
		personService.addPerson(p);
		personlBuilder = new Person.Builder();
		p = personlBuilder.setFirstname("Anne").setLastname("Martin").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email2@getCommunityEmails.fr")
				.build();
		personService.addPerson(p);
		CommunityEmail communityEmail = mainService.getCommunityEmails("cityCommunityEmails");
		assertTrue(communityEmail.getEmails().size() == 2);
	}

	@Test
	void getCommunityEmailsTestException() {
		//
		Throwable exception = assertThrows(EntityNotFoundException.class, () -> mainService.getCommunityEmails("xx"));
		assertTrue(exception.getMessage().contains("No emails found for the city"));
	}

	@Test
	void firestationTest() {
		System.out.println("debut test firestationTest");
		FirestationPerson firestationPerson;
		firestationPerson = mainService.firestation("3");
		assertTrue(firestationPerson.getNbOfAdult() == 10);
		assertTrue(firestationPerson.getNbOfChildren() == 3);
		assertTrue(firestationPerson.getPersonForFirestations().size() == 13);
	}

	@Test
	void floodStationsTest() {
		List<String> stations = new ArrayList<>();
		stations.add("1");
		stations.add("3");
		HouseholdResponse householdResponse = mainService.floodStations(stations);
		assertTrue(householdResponse.getHouseholds().size() == 7);
	}

}
