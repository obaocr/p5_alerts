package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.safetynet.p5_alerts.model.FireStation;

import ch.qos.logback.classic.Logger;

@Repository
public class FireStationDaoImpl implements FireStationDao {

	private static List<FireStation> fireStationData;
	Logger log = (Logger) LoggerFactory.getLogger(FireStationDaoImpl.class);

	public FireStationDaoImpl() {

	}

	// Alimentation des données initiales
	@Override
	public void setFireStations(List<FireStation> fireStations) {
		log.info("FireStationDao deleteAll : delete all FireStation");
		fireStationData = fireStations;
	}

	// Liste des FireStation
	@Override
	public List<FireStation> getAll() {
		log.info("FireStationDao getAll");
		return fireStationData;
	}

	// Ajouter une FireStation
	@Override
	public boolean addFireStation(FireStation fireStation) {
		log.info("FireStationDao addFireStation");
		fireStationData.add(fireStation);
		return true;
	}

	// Mise à jour FireStation d'une adresse
	@Override
	public boolean updateFireStation(FireStation fireStation) {
		log.info("FireStationDao updateFireStation");
		int pos = 0;
		boolean isFound = false;
		for (FireStation fs : fireStationData) {
			if (fs.getAddress().equals(fireStation.getAddress().toString())) {
				fireStationData.set(pos, fireStation);
				isFound = true;
			}
			pos++;
		}
		return isFound;
	}

	// suppression FireStation d'une station / station
	@Override
	public boolean deleteFireStationStation(FireStation fireStation) {
		log.info("FireStationDao deleteFireStationStation");
		boolean isFound = false;
		log.info("dPersonDao eletePerson : delete a person");
		Iterator<FireStation> i = fireStationData.iterator();
		while (i.hasNext()) {
			FireStation o = i.next();
			if (o.getStation().equals(fireStation.getStation().toString())) {
				i.remove();
				isFound = true;
			}
		}
		return isFound;
	}

	// suppression FireStation d'une station / adresse
	@Override
	public boolean deleteFireStationAddress(FireStation fireStation) {
		log.info("FireStationDao deleteFireStationAddress");
		boolean isFound = false;
		log.info("dPersonDao eletePerson : delete a person");
		Iterator<FireStation> i = fireStationData.iterator();
		while (i.hasNext()) {
			FireStation o = i.next();
			if (o.getAddress().equals(fireStation.getAddress().toString())) {
				i.remove();
				isFound = true;
			}
		}
		return isFound;
	}

	// suppression de toutes les FireStation
	@Override
	public boolean deleteAll() {
		log.info("deleteAll : delete all FireStation");
		if (fireStationData != null) {
			fireStationData.clear();
		}
		return true;
	}
	
	// recherche par adresse, rend 1 station, la première si doublon
	@Override
	public FireStation searchByAddress(String address) {
		FireStation fireStation = null;
		boolean isFound = false;
		for (FireStation fs : fireStationData) {
			if (fs.getAddress().equals(address)) {
				fireStation = fs;
				isFound = true;
			}
		}
		return isFound == true ? fireStation : null;
	}
	
	// Recherche des adresses d'une station, on dedoublonne
	@Override
	public List<String> searchByStation(String station) {
		List<String> stations = new ArrayList<>();
		Set<String> setSt = new HashSet<>();
		for (FireStation fs : fireStationData) {
			if(fs.getStation().equals(station)) {
				setSt.add(fs.getAddress());
			}
		}
		if(setSt.size() >0) {
			stations = setSt.stream().collect(Collectors.toList());
		}
		return stations;
	}
}
