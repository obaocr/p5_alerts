package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * Interface for MedicalRecordService
 */
public interface MedicalRecordService {
	public List<MedicalRecord> getMedicalRecords();
	public boolean addMedicalRecord(MedicalRecord medicalRecord);
	public boolean updateMedicalRecord(MedicalRecord medicalRecord);
	public List<MedicalRecord> deleteMedicalRecord(PersonForAPIDelete person);
}
