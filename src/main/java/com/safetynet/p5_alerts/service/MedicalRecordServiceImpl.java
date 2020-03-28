package com.safetynet.p5_alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.model.MedicalRecord;


@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
	@Override
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordDao.getAll();
	}

	@Override
	public boolean addMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.addMedicalRecord(medicalRecord);
	}

	@Override
	public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.updateMedicalRecord(medicalRecord);
	}

	@Override
	public boolean deleteMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.deleteMedicalRecord(medicalRecord);
	}

}
