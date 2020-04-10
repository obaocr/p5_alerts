package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * PersonDao implementation
 */
@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao {

	private static final Logger log = LogManager.getLogger(MedicalRecordDaoImpl.class);
	private static List<MedicalRecord> medicalRecordData = new ArrayList<>();

	public MedicalRecordDaoImpl() {
	}

	public MedicalRecordDaoImpl(List<MedicalRecord> medicalRecords) {
		log.debug("MedicalRecordDao setMedicalRecords");
		if (medicalRecordData != null) {
			medicalRecordData.clear();
		}
		for (MedicalRecord medicalRecord : medicalRecords) {
			medicalRecordData.add(medicalRecord);
		}
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
			if (mr.getFirstname().equals(medicalRecord.getFirstname())
					&& mr.getLastname().equals(medicalRecord.getLastname())) {
				medicalRecordData.set(pos, medicalRecord);
				isFound = true;
			}
			pos++;
		}
		return isFound;
	}

	@Override
	public List<MedicalRecord> deleteMedicalRecord(PersonForAPIDelete person) {
		log.debug("MedicalRecordDao deleteMedicalRecord");
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		Iterator<MedicalRecord> i = medicalRecordData.iterator();
		while (i.hasNext()) {
			MedicalRecord o = i.next();
			if (o.getFirstname().equals(person.getFirstname()) && o.getLastname().equals(person.getLastname())) {
				medicalRecords.add(o);
				i.remove();
			}
		}
		return medicalRecords;
	}

}
