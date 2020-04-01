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

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

@ContextConfiguration
@SpringBootTest
class MedicalRecordServiceTest {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@BeforeAll
	private static void initData() {
		// On ne fait rien, le command line runner est demarré apres le @BeforeAll et
		// charge les donnnées
	}

	@Test
	void getAllTest() {
		int nbItem = medicalRecordService.getMedicalRecords().size();
		assertTrue(nbItem > 1);
	}

	@Test
	void addMedicalRecordTest() throws ParseException {
		MedicalRecord medicalRecord = new MedicalRecord();
		int nbItem = medicalRecordService.getMedicalRecords().size();
		// Ajout d'un item
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Dupont");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		//
		assertTrue(nbItem + 1 == medicalRecordService.getMedicalRecords().size());

	}

	@Test
	void deleteMedicalRecordTest() throws ParseException {
		MedicalRecord medicalRecord = new MedicalRecord();
		PersonForAPIDelete personForAPIDelete = new PersonForAPIDelete();
		personForAPIDelete.setFirstname("Olivier");
		personForAPIDelete.setLastname("Hirch");
		int nbItem1 = medicalRecordService.getMedicalRecords().size();
		// Ajout d'un item
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Hirch");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordService.addMedicalRecord(medicalRecord);
		int nbItem2 = medicalRecordService.getMedicalRecords().size();
		// Suppression
		medicalRecordService.deleteMedicalRecord(personForAPIDelete);
		assertTrue(nbItem1 + 1 == nbItem2);
		assertTrue(nbItem1 == medicalRecordService.getMedicalRecords().size());
	}

	@Test
	void updateMedicalRecordTest() throws ParseException {
		MedicalRecord medicalRecord = new MedicalRecord();
		boolean isUpdated = false;
		// Ajout d'un item
		medicalRecord.setFirstname("Olivier");
		medicalRecord.setLastname("Barberis");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2010");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirin");
		medications.add("Water");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("Milk");
		allergies.add("Wine");
		medicalRecordService.addMedicalRecord(medicalRecord);
		// Modification
		medicalRecord.setAllergies(null);
		medicalRecordService.updateMedicalRecord(medicalRecord);
		// Recherche
		List<MedicalRecord> medicalRecords = medicalRecordService.getMedicalRecords();
		for (MedicalRecord mr : medicalRecords) {
			if (mr.getFirstname().equals(medicalRecord.getFirstname())
					&& mr.getLastname().equals(medicalRecord.getLastname()) && mr.getAllergies() == null)
				isUpdated = true;
		}
		assertTrue(isUpdated == true);

	}

}
