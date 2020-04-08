package com.safetynet.p5_alerts.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;

@ExtendWith(SpringExtension.class)
class MainServiceTest {

	@Autowired
	private MainService mainService;

	@MockBean
	private FireStationDao fireStationDao;

	@MockBean
	private MedicalRecordDao medicalRecordDao;

	@MockBean
	private PersonDao personDao;

	@TestConfiguration
	static class MainServiceTestsContextConfiguration {

		@Bean
		public MainService mainService() {
			return new MainServiceImpl();
		}
	}

	@Test
	void phoneAlertTest() {
		FireStation fireStation = new FireStation("111", "address-111");
		List<FireStation> fireStations = new ArrayList<>();
		fireStations.add(fireStation);
		Mockito.when(fireStationDao.getAll()).thenReturn(fireStations);
		//
		Person person = new Person("Olivier", "Lartiste", "address-111", "Paris", "75000", "888-555", "0102030405");
		Person person2 = new Person("Jean", "Lartiste", "address-111", "Paris", "75000", "888-554", "0102030405");
		List<Person> persons = new ArrayList<>();
		persons.add(person);
		persons.add(person2);
		Mockito.when(personDao.searchByAddress("address-111")).thenReturn(persons);
		//
		PhoneAlert phoneAlert = mainService.phoneAlert("111");
		assertTrue(phoneAlert.getPhones().size() == 2);
	}

	@Test
	void personInfoTest() throws ParseException {
		List<Person> persons = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		//
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("Jean").setLastname("Passepartout").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email1@getCommunityEmails.fr")
				.build();
		persons.add(p);
		personlBuilder = new Person.Builder();
		p = personlBuilder.setFirstname("Jean").setLastname("Passepartout").setAddress("address")
				.setCity("cityCommunityEmails").setZip("zip").setPhone("phone").setEmail("email2@getCommunityEmails.fr")
				.build();
		persons.add(p);
		// Ajout Medical Record
		MedicalRecord medicalRecord = new MedicalRecord();
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
		medicalRecords.add(medicalRecord);
		//
		Mockito.when(personDao.getAll()).thenReturn(persons);
		Mockito.when(medicalRecordDao.getAll()).thenReturn(medicalRecords);
		//
		PersonInfoResponse personInfoResponse = mainService.personInfo("Passepartout", "Jean");
		assertTrue(personInfoResponse.getPersonInfos().size() == 2);
	}
	
	
	@Test
	void childAlertTest() throws ParseException {
		List<Person> persons = new ArrayList<>();
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("prenom-childAlertTest").setLastname("nom-childAlertTest").setAddress("address-childAlertTest")
				.setCity("Emails").setZip("zip").setPhone("phone").setEmail("Emails.fr")
				.build();
		persons.add(p);
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
		medicalRecords.add(medicalRecord);
		//
		Mockito.when(personDao.getAll()).thenReturn(persons);
		Mockito.when(medicalRecordDao.getAll()).thenReturn(medicalRecords);
		Mockito.when(personDao.searchByName("prenom-childAlertTest","nom-childAlertTest")).thenReturn(p);
		//
		ChildAlertResponse childAlertResponse = mainService.childAlert("address-childAlertTest");
		assertTrue(childAlertResponse.getChildAlerts().size() == 1);
	}
	
	
	@Test
	void fireTest() throws ParseException {
		List<Person> persons = new ArrayList<>();
		//
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstname("prenom-fireTest1");
		medicalRecord.setLastname("nom-fireTest1");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2018");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		//
		FireStation fireStation = new FireStation("station-fireTest1","address-fireTest1");
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname("prenom-fireTest1").setLastname("nom-fireTest1").setAddress("address-fireTest1")
				.setCity("city").setZip("zip").setPhone("phone").setEmail("Emails.fr")
				.build();
		persons.add(p);
		//
		Mockito.when(personDao.searchByAddress("address-fireTest1")).thenReturn(persons);
		Mockito.when(fireStationDao.searchByAddress("address-fireTest1")).thenReturn(fireStation);
		Mockito.when(medicalRecordDao.searchByName("prenom-fireTest1","nom-fireTest1")).thenReturn(medicalRecord);
		//
		PersonForFirestationAddressResponse personForFirestationAddressResponse = mainService.fire("address-fireTest1");
		assertTrue(personForFirestationAddressResponse.getPersonForFirestationAddress().size() == 1);
	}

}
