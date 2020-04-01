package com.safetynet.p5_alerts.dao;

import java.util.Iterator;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

import ch.qos.logback.classic.Logger;

@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {
	
	private static List<MedicalRecord> medicalRecordData;
	Logger log = (Logger) LoggerFactory.getLogger(MedicalRecordDaoImpl.class);
	
	public MedicalRecordDaoImpl() {
		
	}
	
	@Override
	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		log.debug("MedicalRecordDao setMedicalRecords");
		medicalRecordData = medicalRecords;
	}

	@Override
	public List<MedicalRecord> getAll() {
		log.debug("MedicalRecordDao getAll");
		return medicalRecordData;
	}
	
	// Recherche par prenom, nom
	@Override
	public MedicalRecord searchByName(String firstName, String lastName) {
		log.debug("MedicalRecordDao searchByName");
		for (MedicalRecord medicalRecord : medicalRecordData) {
			if (medicalRecord.getFirstname().equals(firstName) && medicalRecord.getLastname().equals(lastName)) {
				return medicalRecord;
			}
		}
		return null;
	}

	@Override
	public boolean addMedicalRecord(MedicalRecord medicalRecord) {
		log.debug("MedicalRecordDao addMedicalRecord");
		medicalRecordData.add(medicalRecord);
		return true;
	}

	@Override
	public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
		log.debug("MedicalRecordDao addMedicalRecord");
		int pos = 0;
		boolean isFound = false;
		for (MedicalRecord mr : medicalRecordData) {
			if (mr.getFirstname().equals(medicalRecord.getFirstname().toString()) && mr.getLastname().equals(medicalRecord.getLastname().toString())) {
				medicalRecordData.set(pos, medicalRecord);
				isFound = true;
			}
			pos++;
		}
		return isFound;
	}

	@Override
	public boolean deleteMedicalRecord(PersonForAPIDelete person) {
		log.debug("MedicalRecordDao deleteMedicalRecord");
		boolean isFound = false;
		Iterator<MedicalRecord> i = medicalRecordData.iterator();
		while (i.hasNext()) {
			MedicalRecord o = i.next();
			if (o.getFirstname().equals(person.getFirstname().toString()) && o.getLastname().equals(person.getLastname().toString())) {
				i.remove();
				isFound = true;
			}
		}
		return isFound;
	}

	@Override
	public boolean deleteAll() {
		log.debug("MedicalRecordDao deleteAll");
		if (medicalRecordData != null) {
			medicalRecordData.clear();
		}
		return true;
	}

}
