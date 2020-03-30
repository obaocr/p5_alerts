package com.safetynet.p5_alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFirestation;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.model.PhoneAlert;
import com.safetynet.p5_alerts.util.Utils;

import ch.qos.logback.classic.Logger;

@Service
public class MainServiceImpl implements MainService {

	Logger log = (Logger) LoggerFactory.getLogger(MainServiceImpl.class);

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@Autowired
	private FireStationDao fireStationDao;

	@Autowired
	private PersonDao personDao;

	@Override
	public CommunityEmail getCommunityEmails(String city) {
		return personDao.getCommunityEmails(city);
	}

	@Override
	public List<PersonInfo> personInfo(String lastName, String firstName) {
		log.info("personInfo : infos persons for a 'name'");
		List<Person> persons = personDao.getAll();
		List<MedicalRecord> medicalRecords = medicalRecordDao.getAll();
		List<PersonInfo> personInfos = new ArrayList<>();
		PersonInfo personInfo;
		for (Person psn : persons) {
			for (MedicalRecord mr : medicalRecords) {
				if (psn.getFirstname().equals(firstName) && psn.getLastname().equals(lastName)
						&& mr.getFirstname().equals(firstName) && mr.getLastname().equals(lastName)) {
					System.out.println("match !");
					personInfo = new PersonInfo();
					int age = Utils.calculateAgeFromBirthDate(mr.getBirthDate());
					personInfo.setFirstname(psn.getFirstname().toString());
					personInfo.setLastname(psn.getLastname().toString());
					personInfo.setAge(age);
					personInfo.setEmail(psn.getEmail().toString());
					personInfo.setAddress(psn.getAddress().toString());
					personInfo.setAllergies(mr.getAllergies());
					personInfo.setMedications(mr.getMedications());
					personInfos.add(personInfo);
				}
			}
		}
		return personInfos;
	}

	@Override
	public List<ChildAlert> childAlert(@RequestParam String address) {
		log.info("childAlert : list of children from an address");
		// Recherche des enfants
		List<ChildAlert> childAlerts = new ArrayList<>();
		List<Person> membres;
		ChildAlert childAlert;

		// Recherche des medical records de même nom/prenom et <= 18 ans
		for (MedicalRecord mr : medicalRecordDao.getAll()) {
			if (Utils.calculateAgeFromBirthDate(mr.getBirthDate()) < 19
					&& personDao.searchByName(mr.getFirstname(), mr.getLastname()) != null
					&& personDao.searchByName(mr.getFirstname(), mr.getLastname()).getAddress().equals(address)) {
				membres = new ArrayList<>();
				;
				childAlert = new ChildAlert();
				childAlert.setFirstname(mr.getFirstname());
				childAlert.setLastname(mr.getLastname());
				childAlert.setAge(Utils.calculateAgeFromBirthDate(mr.getBirthDate()));
				// Recherche des membres habitants a cette adresse et <> de cet enfant
				for (Person psn : personDao.getAll()) {
					if (psn.getAddress().equals(address) && !(psn.getFirstname().equals(mr.getFirstname())
							&& psn.getLastname().equals(mr.getLastname()))) {
						System.out.println("membre trouve : " + psn.getFirstname());
						membres.add(psn);
					}
				}
				System.out.println("fin boucle");
				childAlert.setPersons(membres);
				childAlerts.add(childAlert);
			}
		}
		System.out.println("liste 1 childAlerts size : " + childAlerts.size());
		return childAlerts;
	}

	@Override
	public FirestationPerson firestation(String station) {
		log.info("PersonForFiretationResult : infos persons for a station");
		List<Person> persons = personDao.getAll();
		List<FireStation> firestations = fireStationDao.getAll();
		FirestationPerson firestationPerson = new FirestationPerson();
		List<PersonForFirestation> personForFirestations = new ArrayList<>();
		PersonForFirestation personForFirestation;
		MedicalRecord medicalRecord;
		int nbOfAdult = 0;
		int nbOfChildren = 0;
		int age;
		boolean isStationFound = false;

		for (FireStation firestation : firestations) {
			if (firestation.getStation().equals(station)) {
				isStationFound = true;
				for (Person psn : persons) {
					if (psn.getAddress().equals(firestation.getAddress())) {
						medicalRecord = medicalRecordDao.searchByName(psn.getFirstname(), psn.getLastname());
						if (medicalRecord != null) {
							age = Utils.calculateAgeFromBirthDate(medicalRecord.getBirthDate());
							if (age > 18) {
								nbOfAdult++;
							} else {
								nbOfChildren++;
							}
						}
						personForFirestation = new PersonForFirestation();
						personForFirestation.setFirstname(psn.getFirstname());
						personForFirestation.setLastname(psn.getLastname());
						personForFirestation.setAddress(psn.getAddress());
						personForFirestation.setCity(psn.getCity());
						personForFirestation.setZip(psn.getZip());
						personForFirestation.setPhone(psn.getPhone());
						personForFirestations.add(personForFirestation);
					}
				}
			}
		}
		if (isStationFound) {
			firestationPerson.setNbOfChildren(nbOfChildren);
			firestationPerson.setNbOfAdult(nbOfAdult);
			firestationPerson.setPersonForFirestations(personForFirestations);
		}
		// gérer code retour not found ...
		return isStationFound == true ? firestationPerson : null;
	}
	
	// Les telephones des personnes desservies par adresse de la station
	// On dedoublonne
	@Override
	public PhoneAlert phoneAlert(String firestation_number) {
		log.info("phoneAlert : Phone for an address firestation");
		PhoneAlert phoneAlert = new PhoneAlert();
		Set<String> setPhone = new HashSet<>();
		List<String> phones = new ArrayList<>();
		List<Person> persons;
		for(FireStation fireStation : fireStationDao.getAll()) {
			if(fireStation.getStation().equals(firestation_number)) {
				persons = personDao.searchByAddress(fireStation.getAddress());
				for (Person psn : persons) {
					setPhone.add(psn.getPhone());
				}
			}
		}
		if (setPhone.size() > 0) {
			phones = setPhone.stream().collect(Collectors.toList());
			phoneAlert.setPhones(phones);
		}
		return phoneAlert;
	}
	
	@Override
	// Les personnes a une adresse avec info Firestatione et Medicalrecords
	public List<PersonForFirestationAddress> fire(String address) {
		log.info("fire : Persons infos for a firestation address");
		List<PersonForFirestationAddress> lpersonForFirestationAddress = new ArrayList<>();
		FireStation fireStation;
		MedicalRecord medicalRecord;
		PersonForFirestationAddress personForFirestationAddress;
		int age;
		for (Person psn : personDao.searchByAddress(address)) {
			personForFirestationAddress = new PersonForFirestationAddress();
			personForFirestationAddress.setFirstname(psn.getFirstname());
			personForFirestationAddress.setLastname(psn.getLastname());
			personForFirestationAddress.setAddress(psn.getAddress());
			fireStation = fireStationDao.searchByAddress(psn.getAddress());
			if (fireStation != null) {
				personForFirestationAddress.setStation(fireStation.getStation());
			}
			medicalRecord = medicalRecordDao.searchByName(psn.getFirstname(), psn.getLastname());
			if(medicalRecord != null) {
				age = Utils.calculateAgeFromBirthDate(medicalRecord.getBirthDate());
				personForFirestationAddress.setAge(age);
				personForFirestationAddress.setMedications(medicalRecord.getMedications());
				personForFirestationAddress.setAllergies(medicalRecord.getAllergies());
			}
			lpersonForFirestationAddress.add(personForFirestationAddress);
			
		}
		return lpersonForFirestationAddress;
	}

}
