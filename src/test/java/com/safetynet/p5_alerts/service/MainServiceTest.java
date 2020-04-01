package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.Household;
import com.safetynet.p5_alerts.model.HouseholdResponse;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;

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

	@BeforeAll
	private static void initData() {
		// On ne fait rien, le command line runner est demarré apres le @BeforeAll et
		// charge les donnnées
	}

	@Test
	void getCommunityEmails() {
		//
		System.out.println("** MainService getCommunityEmails/ fireStationService : "
				+ fireStationService.getFireStations().size());
		System.out.println("** MainService getCommunityEmails/ personService : " + personService.getPersons().size());
		System.out.println("** MainService getCommunityEmails/ medicalRecordService : "
				+ medicalRecordService.getMedicalRecords().size());
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
	void childAlertTest() {
		ChildAlertResponse childAlertResponse = new ChildAlertResponse();
		childAlertResponse = mainService.childAlert("1509 Culver St");
		System.out.println(
				"MainServiceTest childAlertTest personService.getPersons() :" + personService.getPersons().size());
		System.out.println("MainServiceTest childAlertTest ********** !!!!! ***********  :" + childAlertResponse.getChildAlerts().size());
		assertTrue(childAlertResponse.getChildAlerts().size() == 2);
	}

	@Test
	void firestationTest() {
		FirestationPerson firestationPerson;
		firestationPerson = mainService.firestation("3");
		System.out.println("MainServiceTest firestationTest getNbOfAdult. :" + firestationPerson.getNbOfAdult());
		System.out.println("MainServiceTest firestationTest getNbOfChildren. :" + firestationPerson.getNbOfChildren());
		System.out.println("MainServiceTest firestationTest getPersonForFirestations. :"
				+ firestationPerson.getPersonForFirestations().size());
		assertTrue(firestationPerson.getNbOfAdult() == 10);
		assertTrue(firestationPerson.getNbOfChildren() == 3);
		assertTrue(firestationPerson.getPersonForFirestations().size() == 13);
	}

	@Test
	void phoneAlertTest() {
		PhoneAlert phoneAlert = mainService.phoneAlert("3");
		System.out.println("MainServiceTest phoneAlertTest getNbOfAdult. :" + phoneAlert.getPhones().size());
		assertTrue(phoneAlert.getPhones().size() == 7);
	}

	@Test
	void fireTest() {
		PersonForFirestationAddressResponse personForFirestationAddressResponse = new PersonForFirestationAddressResponse();
		personForFirestationAddressResponse = mainService.fire("1509 Culver St");
		System.out.println("MainServiceTest fireTest nb  :" + personForFirestationAddressResponse.getPersonForFirestationAddress().size());
		assertTrue(personForFirestationAddressResponse.getPersonForFirestationAddress().size() == 5);
	}

	@Test
	void floodStationsTest() {
		List<String> stations = new ArrayList<>();
		stations.add("1");
		stations.add("3");
		HouseholdResponse householdResponse = new HouseholdResponse();
		householdResponse = floodStationService.floodStations(stations);
		System.out.println("MainServiceTest floodStationsTest nb  :" + householdResponse.getHouseholds().size());
		assertTrue(householdResponse.getHouseholds().size() == 7);
	}

}
