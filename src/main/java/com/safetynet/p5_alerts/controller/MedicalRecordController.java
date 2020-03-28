package com.safetynet.p5_alerts.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.service.MedicalRecordService;

import ch.qos.logback.classic.Logger;

@RestController
public class MedicalRecordController {
	Logger log = (Logger) LoggerFactory.getLogger(MedicalRecordController.class);

	@Autowired
	private MedicalRecordService medicalRecordService;

	@GetMapping(value = "medicalrecord/all")
	public List<MedicalRecord> all() {
		log.info("medicalrecords/all : list of medicalrecords");
		return medicalRecordService.getMedicalRecords();
	}

	@PostMapping(value = "medicalrecord")
	public boolean addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.info("Create a medicalrecord");
		return medicalRecordService.addMedicalRecord(medicalRecord);
	}

	@PutMapping(value = "medicalrecord")
	public boolean updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.info("Update a medicalrecord by address");
		return medicalRecordService.updateMedicalRecord(medicalRecord);
	}

	@DeleteMapping(value = "medicalrecord")
	public boolean deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		log.info("Detete a medicalrecord");
		return medicalRecordService.deleteMedicalRecord(medicalRecord);
	}

}
