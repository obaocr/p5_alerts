package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;
import com.safetynet.p5_alerts.service.MedicalRecordService;
import com.safetynet.p5_alerts.util.EntityIllegalArgumentException;

/**
 * Controller for MedicalRecord object
 */
@RestController
public class MedicalRecordController {
	private static final Logger log = LogManager.getLogger(MedicalRecordController.class);

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping(value = "medicalrecord/all")
	public List<MedicalRecord> all() {
		log.debug("medicalrecords/all : list of medicalrecords");
		return medicalRecordService.getMedicalRecords();
	}

	private void checkInput(MedicalRecord medicalRecord) {
		if (medicalRecord == null || medicalRecord.getFirstname().isEmpty() || medicalRecord.getLastname().isEmpty()) {
			throw new EntityIllegalArgumentException("Firstname and Lastname are mandatory");
		}
	}

	private void checkInputPersonForAPIDelete(PersonForAPIDelete person) {
		if (person == null || person.getFirstname().isEmpty() || person.getLastname().isEmpty()) {
			throw new EntityIllegalArgumentException("Firstname and Lastname are mandatory");
		}
	}

	@PostMapping(value = "medicalrecord")
	public boolean addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.debug("Create a medicalrecord");
		checkInput(medicalRecord);
		return medicalRecordService.addMedicalRecord(medicalRecord);
	}

	@PutMapping(value = "medicalrecord")
	public boolean updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.debug("Update a medicalrecord by address");
		checkInput(medicalRecord);
		return medicalRecordService.updateMedicalRecord(medicalRecord);
	}

	@DeleteMapping(value = "medicalrecord")
	public boolean deleteMedicalRecord(@RequestBody PersonForAPIDelete person) {
		log.debug("Detete a medicalrecord");
		checkInputPersonForAPIDelete(person);
		return medicalRecordService.deleteMedicalRecord(person);
	}

}
