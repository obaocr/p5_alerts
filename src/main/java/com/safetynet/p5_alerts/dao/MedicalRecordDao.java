package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

public interface MedicalRecordDao {
	// Alimentation des données initiales
	void setMedicalRecords(List<MedicalRecord> medicalRecords);

	// Liste des MedicalRecord
	public List<MedicalRecord> getAll();

	// Recherche par prenom, nom
	public MedicalRecord searchByName(String firstName, String lastName);

	// Ajouter une MedicalRecord
	public boolean addMedicalRecord(MedicalRecord medicalRecord);

	// Mise à jour MedicalRecord
	public boolean updateMedicalRecord(MedicalRecord medicalRecord);

	// suppression MedicalRecord
	public boolean deleteMedicalRecord(PersonForAPIDelete person);

	// suppression de toutes les MedicalRecord
	public boolean deleteAll();
}
