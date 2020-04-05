package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;

/**
 * Interface for FireStationDao
 */
public interface FireStationDao {
	public List<FireStation> getAll();
	public boolean addFireStation(FireStation fireStation);
	public boolean updateFireStation(FireStation fireStation);
	public List<FireStation> deleteFireStationStation(FireStation fireStation);
	public List<FireStation> deleteFireStationAddress(FireStation fireStation);
	public FireStation searchByAddress(String address);
	public List<String> searchByStation(String station);

}