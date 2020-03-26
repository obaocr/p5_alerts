package com.safetynet.p5_alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.model.FireStation;

@Service
public class FireStationServiceImpl implements FireStationService {

	@Autowired
	private FireStationDao fireStationDao;

	@Override
	public List<FireStation> getFireStations() {
		return fireStationDao.getAll();
	}

	@Override
	public boolean addFireStation(FireStation fireStation) {
		return fireStationDao.addFireStation(fireStation);
	}

	@Override
	public boolean updateFireStation(FireStation fireStation) {
		return fireStationDao.updateFireStation(fireStation);
	}

	@Override
	public boolean deleteFireStationbyStation(FireStation fireStation) {
		return fireStationDao.deleteFireStationStation(fireStation);
	}

	@Override
	public boolean deleteFireStationbyAddress(FireStation fireStation) {
		return fireStationDao.deleteFireStationAddress(fireStation);
	}

}
