package com.safetynet.p5_alerts.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
/*
 * Controllers tests end/end without mock
 * Test file for input Data
 */
class MainControllerTest {

	@Autowired
	private MainController mainController;

	@Test
	public void getCommunityEmailsShouldReturnEmails() throws Exception {
		assertTrue(mainController.getCommunityEmails("Culver").getEmails().size() > 0);
	}

	@Test
	public void personInfoShouldReturnpersonInfos() throws Exception {
		assertTrue(mainController.personInfo("Boyd","John").getPersonInfos().size() > 0);
	}
	
	@Test
	public void childAlertShouldReturnchildAlerts() throws Exception {
		assertTrue(mainController.childAlert("1509 Culver St").getChildAlerts().size() > 0);
	}

	@Test
	public void firestationPersonShouldReturnPersonForFirestations() throws Exception {
		assertTrue(mainController.firestationPerson("3").getPersonForFirestations().size() > 0);
	}

	@Test
	public void phoneAlertPersonShouldReturnPhones() throws Exception {
		assertTrue(mainController.phoneAlert("3").getPhones().size() > 0);
	}

	@Test
	public void fireShouldReturnPersonForFirestationAddress() throws Exception {
		assertTrue(mainController.fire("1509 Culver St").getPersonForFirestationAddress().size() > 0);
	}

	@Test
	public void floodStationsShouldReturnHouseholdResponse() throws Exception {
		List<String> stations = new ArrayList<>();
		stations.add("1");
		stations.add("3");
		assertTrue(mainController.floodStations(stations).getHouseholds().size() > 0);
	}

}
