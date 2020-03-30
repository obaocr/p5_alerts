package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.FireStation;

public interface FireStationDao {
	// Alimentation des données initiales
	void setFireStations(List<FireStation> fireStation);

	// Liste des FireStation
	public List<FireStation> getAll();

	// Ajouter une FireStation
	public boolean addFireStation(FireStation fireStation);

	// Mise à jour FireStation
	public boolean updateFireStation(FireStation fireStation);

	// suppression FireStation
	public boolean deleteFireStationStation(FireStation fireStation);

	public boolean deleteFireStationAddress(FireStation fireStation);

	// suppression de toutes les FireStation
	public boolean deleteAll();

	// recherche par adresse, rend 1 station, la première si doublon
	public FireStation searchByAddress(String address);
	
	// recherche des adresses d'une station
	public List<String> searchByStation(String station);

}