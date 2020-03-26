package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;

public interface MedicalRecordDao {
	// Alimentation des données initiales
	void setMedicalRecords(List<MedicalRecord> medicalRecord);

	// Liste des MedicalRecord
	public List<MedicalRecord> getAll();

	// Ajouter une MedicalRecord
	public boolean addMedicalRecord(MedicalRecord medicalRecord);

	// Mise à jour MedicalRecord
	public boolean updateMedicalRecord(MedicalRecord medicalRecord);

	// suppression MedicalRecord
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord);

	// suppression de toutes les MedicalRecord
	public boolean deleteAll();
}
