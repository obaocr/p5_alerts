package com.safetynet.p5_alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.Household;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFlood;
import com.safetynet.p5_alerts.util.Utils;

import ch.qos.logback.classic.Logger;

@Service
public class FloodStationServiceImpl implements FloodStationService {

	Logger log = (Logger) LoggerFactory.getLogger(MainServiceImpl.class);

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@Autowired
	private FireStationDao fireStationDao;

	@Autowired
	private PersonDao personDao;

	// Chargement dans une Set des adresses des stations (dedoublonées)
	private Set<String> getAdressStations(List<String> stations) {
		Set<String> setAddress = new HashSet<>();
		for (String station : stations) {
			List<String> address = fireStationDao.searchByStation(station);
			for (String adr : address) {
				setAddress.add(adr);
			}
		}
		return setAddress;
	}

	private List<PersonForFlood> getpersonForFlood(List<Person> persons) {
		List<PersonForFlood> personForFloods = new ArrayList<>();
		PersonForFlood personForFlood;
		MedicalRecord medicalRecord;
		for (Person psn : persons) {
			personForFlood = new PersonForFlood();
			personForFlood.setFirstname(psn.getFirstname());
			personForFlood.setLastname(psn.getLastname());
			personForFlood.setPhone(psn.getPhone());
			medicalRecord = medicalRecordDao.searchByName(psn.getFirstname(), psn.getLastname());
			if (medicalRecord != null) {
				personForFlood.setAge(Utils.calculateAgeFromBirthDate(medicalRecord.getBirthDate()));
				personForFlood.setAllergies(medicalRecord.getAllergies());
				personForFlood.setMedications(medicalRecord.getMedications());
			}
			personForFloods.add(personForFlood);
		}
		return personForFloods;
	}

	// Les foyers et personnes pour une liste de stations
	@Override
	public List<Household> floodStations(List<String> stations) {
		log.info("floodStations : Households & Persons infos for a firestations list");
		List<Household> households = new ArrayList<>();
		Household household;
		String address;

		// On charge une Set des adresses des stations
		Set<String> setAddress = getAdressStations(stations);

		// On parcourt la Set des adresses
		Iterator<String> adr = setAddress.iterator();
		while (adr.hasNext()) {
			address = adr.next();
			List<Person> persons = personDao.searchByAddress(address);
			if (persons != null && persons.size() > 0) {
				household = new Household();
				household.setAddress(address);
				List<PersonForFlood> personForFloods = getpersonForFlood(persons);
				household.setPersonForFloods(personForFloods);
				households.add(household);
			}
		}
		return households;
	}

}
