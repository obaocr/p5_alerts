package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * Interface for MedicalRecordDao
 */
public interface MedicalRecordDao {
	public List<MedicalRecord> getAll();
	public MedicalRecord searchByName(String firstName, String lastName);
	public boolean addMedicalRecord(MedicalRecord medicalRecord);
	public boolean updateMedicalRecord(MedicalRecord medicalRecord);
	public boolean deleteMedicalRecord(PersonForAPIDelete person);
}
