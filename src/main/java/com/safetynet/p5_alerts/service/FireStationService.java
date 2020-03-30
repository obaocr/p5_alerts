package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;

public interface FireStationService {
	public List<FireStation> getFireStations();
	public boolean addFireStation(FireStation fireStation);
	public boolean updateFireStation(FireStation fireStation);
	public boolean deleteFireStationbyStation(FireStation fireStation);
	public boolean deleteFireStationbyAddress(FireStation fireStation);
}