package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.MedicalRecord;

public interface MedicalRecordService {
	public List<MedicalRecord> getMedicalRecords();
	public boolean addMedicalRecord(MedicalRecord medicalRecord);
	public boolean updateMedicalRecord(MedicalRecord medicalRecord);
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord);
}