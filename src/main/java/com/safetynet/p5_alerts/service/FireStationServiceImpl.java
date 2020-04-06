package com.safetynet.p5_alerts.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.model.FireStation;

@Service
public class FireStationServiceImpl implements FireStationService {

	private static final Logger log = LogManager.getLogger(FireStationServiceImpl.class);

	@Autowired
	private FireStationDao fireStationDao;

	@Override
	public List<FireStation> getFireStations() {
		log.debug("FireStationServiceImpl : getFireStations");
		return fireStationDao.getAll();
	}

	@Override
	public boolean addFireStation(FireStation fireStation) {
		log.debug("FireStationServiceImpl : addFireStation");
		return fireStationDao.addFireStation(fireStation);
	}

	@Override
	public boolean updateFireStation(FireStation fireStation) {
		log.debug("FireStationServiceImpl : updateFireStation");
		return fireStationDao.updateFireStation(fireStation);
	}

	@Override
	public List<FireStation> deleteFireStationbyStation(FireStation fireStation) {
		log.debug("FireStationServiceImpl : deleteFireStationbyStation");
		return fireStationDao.deleteFireStationStation(fireStation);
	}

	@Override
	public List<FireStation> deleteFireStationbyAddress(FireStation fireStation) {
		log.debug("FireStationServiceImpl : deleteFireStationbyAddress");
		return fireStationDao.deleteFireStationAddress(fireStation);
	}

}
