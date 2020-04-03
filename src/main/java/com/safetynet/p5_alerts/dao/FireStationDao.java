package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;

/**
 * Interface for FireStationDao
 */
public interface FireStationDao {
	// Alimentation des données initiales
	void setFireStations(List<FireStation> fireStation);

	// Liste des FireStation
	public List<FireStation> getAll();

	// Ajouter une FireStation
	public boolean addFireStation(FireStation fireStation);

	// Mise à jour FireStation
	public boolean updateFireStation(FireStation fireStation);

	// suppression FireStation par station
	public List<FireStation> deleteFireStationStation(FireStation fireStation);
	
	// suppression FireStation par address
	public List<FireStation> deleteFireStationAddress(FireStation fireStation);

	// suppression de toutes les FireStation
	public boolean deleteAll();

	// recherche par adresse, rend 1 station, la première si doublon
	public FireStation searchByAddress(String address);
	
	// recherche des adresses d'une station
	public List<String> searchByStation(String station);

}