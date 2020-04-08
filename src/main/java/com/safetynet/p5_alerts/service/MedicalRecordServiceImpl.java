package com.safetynet.p5_alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.PersonForAPIDelete;

/**
 * 
 * MedicalRecordService implementation
 *
 */
@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	private static final Logger log = LogManager.getLogger(MedicalRecordServiceImpl.class);
	
	@Autowired
	
	private MedicalRecordDao medicalRecordDao;
	
	@Override
	public List<MedicalRecord> getMedicalRecords() {
		log.debug("MedicalRecordServiceImpl : getMedicalRecords");
		return medicalRecordDao.getAll();
	}

	@Override
	public boolean addMedicalRecord(MedicalRecord medicalRecord) {
		log.debug("MedicalRecordServiceImpl : addMedicalRecord");
		return medicalRecordDao.addMedicalRecord(medicalRecord);
	}

	@Override
	public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
		log.debug("MedicalRecordServiceImpl : updateMedicalRecord");
		return medicalRecordDao.updateMedicalRecord(medicalRecord);
	}

	@Override
	public boolean deleteMedicalRecord(PersonForAPIDelete person) {
		log.debug("MedicalRecordServiceImpl : deleteMedicalRecord");
		return medicalRecordDao.deleteMedicalRecord(person);
	}

}
