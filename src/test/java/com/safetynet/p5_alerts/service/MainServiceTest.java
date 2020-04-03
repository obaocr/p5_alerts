package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.HouseholdResponse;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;
import com.safetynet.p5_alerts.util.EntityNotFoundException;

@ContextConfiguration
@SpringBootTest
class MainServiceTest {

	@Autowired
	private MainService mainService;

	@Autowired
	private FireStationService fireStationService;

	@Autowired
	private PersonService personService;

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Autowired
	private FloodStationService floodStationService;

	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private FireStationDao fireStationDao;
	
	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	@Autowired
	private DataService dataService;
	
	@BeforeEach
	private void initData() throws IOException {
		// Before Each car le Before All est exécuté avant le conetxte spring et donc avant le commandLineRunner
		personDao.deleteAll();
		fireStationDao.deleteAll();
		medicalRecordDao.deleteAll();
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
		Throwable exception = assertThrows(EntityNotFoundException.class,
				() -> mainService.getCommunityEmails("xx"));
		assertTrue(exception.getMessage().contains("No emails found for the city"));
	}
	
	@Test
	void personInfoTest() throws ParseException {
		// Ajout psn
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Jean").setLastname("Passepartout").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email1@getCommunityEmails.fr")
				.build();
		personService.addPerson(p);
		personlBuilder = new Person.Builder();
		p = personlBuilder.setFirstname("Jean").setLastname("Passepartout").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email2@getCommunityEmails.fr")
				.build();
		personService.addPerson(p);
		// Ajout Medical Record
		MedicalRecord medicalRecord = new MedicalRecord();
		// Ajout d'un item
		medicalRecord.setFirstname("Jean");
		medicalRecord.setLastname("Passepartout");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		PersonInfoResponse personInfoResponse = mainService.personInfo("Passepartout", "Jean");
		assertTrue(personInfoResponse.getPersonInfos().size() == 2);
	}

	@Test
	void childAlertTest() throws ParseException {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("prenom-childAlertTest").setLastname("nom-childAlertTest").setAddress("address-childAlertTest")
				.setCity("Emails").setZip("zip").setPhone("phone").setEmail("Emails.fr")
				.build();
		personService.addPerson(p);
		//
		MedicalRecord medicalRecord = new MedicalRecord();
		// Ajout d'un item
		medicalRecord.setFirstname("prenom-childAlertTest");
		medicalRecord.setLastname("nom-childAlertTest");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2018");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		ChildAlertResponse childAlertResponse = mainService.childAlert("address-childAlertTest");
		assertTrue(childAlertResponse.getChildAlerts().size() == 1);
	}

	@Test
	void firestationTest() {
		FirestationPerson firestationPerson;
		firestationPerson = mainService.firestation("3");
		assertTrue(firestationPerson.getNbOfAdult() == 10);
		assertTrue(firestationPerson.getNbOfChildren() == 3);
		assertTrue(firestationPerson.getPersonForFirestations().size() == 13);
	}

	@Test
	void phoneAlertTest() {
		PhoneAlert phoneAlert = mainService.phoneAlert("3");
		assertTrue(phoneAlert.getPhones().size() == 7);
	}

	@Test
	void fireTest() {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("prenom-fireTest1").setLastname("nom-fireTest1").setAddress("address-fireTest1")
				.setCity("city").setZip("zip").setPhone("phone").setEmail("Emails.fr")
				.build();
		personService.addPerson(p);
		FireStation fireStation = new FireStation();
		fireStation.setAddress("address-fireTest1");
		fireStation.setStation("station-fireTest1");
		fireStationService.addFireStation(fireStation);
		PersonForFirestationAddressResponse personForFirestationAddressResponse = mainService.fire("address-fireTest1");
		assertTrue(personForFirestationAddressResponse.getPersonForFirestationAddress().size() > 0);
	}

	@Test
	void floodStationsTest() {
		List<String> stations = new ArrayList<>();
		stations.add("1");
		stations.add("3");
		HouseholdResponse householdResponse = floodStationService.floodStations(stations);
		assertTrue(householdResponse.getHouseholds().size() == 7);
	}

}
