package com.safetynet.p5_alerts.dao;

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

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;
import com.safetynet.p5_alerts.service.DataService;

@SpringBootTest
class MedicalRecordDaoIntgTest {

	@Autowired
	private DataService dataService;

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@BeforeEach
	private void initData() throws IOException {
		dataService.loadData();
	}

	@Test
	void getAllTest() {
		int nbItem = medicalRecordDao.getAll().size();
		assertTrue(nbItem > 1);
	}

	@Test
	void searchByNameTest() throws ParseException {
		MedicalRecord medicalRecord = new MedicalRecord();
		// Ajout d'un item
		medicalRecord.setFirstname("Xavier-Emmanuel");
		medicalRecord.setLastname("Dupont");
		Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("12/12/1947");
		medicalRecord.setBirthDate(birthDate);
		List<String> medications = new ArrayList<>();
		medications.add("Aspirine");
		medicalRecord.setMedications(medications);
		List<String> allergies = new ArrayList<>();
		medicalRecord.setAllergies(allergies);
		allergies.add("milk");
		medicalRecordDao.addMedicalRecord(medicalRecord);
		assertTrue(medicalRecordDao.searchByName("Xavier-Emmanuel", "Dupont").getBirthDate().equals(birthDate));

	}

	@Test
	void addMedicalRecordTest() throws ParseException {
		MedicalRecord medicalRecord = new MedicalRecord();
		int nbItem = medicalRecordDao.getAll().size();
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
		medicalRecordDao.addMedicalRecord(medicalRecord);
		//
		assertTrue(nbItem + 1 == medicalRecordDao.getAll().size());

	}

	@Test
	void deleteMedicalRecordTest() throws ParseException {
		PersonForAPIDelete personForAPIDelete = new PersonForAPIDelete();
		personForAPIDelete.setFirstname("Olivier");
		personForAPIDelete.setLastname("Hirch");
		MedicalRecord medicalRecord = new MedicalRecord();
		int nbItem1 = medicalRecordDao.getAll().size();
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
		medicalRecordDao.addMedicalRecord(medicalRecord);
		int nbItem2 = medicalRecordDao.getAll().size();
		// Suppression
		medicalRecordDao.deleteMedicalRecord(personForAPIDelete);
		assertTrue(nbItem1 + 1 == nbItem2);
		assertTrue(nbItem1 == medicalRecordDao.getAll().size());
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
		medicalRecordDao.addMedicalRecord(medicalRecord);
		// Modification
		medicalRecord.setAllergies(null);
		medicalRecordDao.updateMedicalRecord(medicalRecord);
		// Recherche
		List<MedicalRecord> medicalRecords = medicalRecordDao.getAll();
		for (MedicalRecord mr : medicalRecords) {
			if (mr.getFirstname().equals(medicalRecord.getFirstname())
					&& mr.getLastname().equals(medicalRecord.getLastname()) && mr.getAllergies() == null)
				isUpdated = true;
		}
		assertTrue(isUpdated == true);

	}

}
