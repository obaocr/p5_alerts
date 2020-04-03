package com.safetynet.p5_alerts.service;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;

/**
 * Interface for FireStationService
 */
public interface FireStationService {
	public List<FireStation> getFireStations();
	public boolean addFireStation(FireStation fireStation);
	public boolean updateFireStation(FireStation fireStation);
	public List<FireStation> deleteFireStationbyStation(FireStation fireStation);
	public List<FireStation> deleteFireStationbyAddress(FireStation fireStation);
}
