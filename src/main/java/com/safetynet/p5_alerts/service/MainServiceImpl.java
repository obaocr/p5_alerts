package com.safetynet.p5_alerts.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.model.ChildAlert;
import com.safetynet.p5_alerts.model.ChildAlertResponse;
import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.FirestationPerson;
import com.safetynet.p5_alerts.model.Household;
import com.safetynet.p5_alerts.model.HouseholdResponse;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.model.PersonForFirestation;
import com.safetynet.p5_alerts.model.PersonForFirestationAddress;
import com.safetynet.p5_alerts.model.PersonForFirestationAddressResponse;
import com.safetynet.p5_alerts.model.PersonForFlood;
import com.safetynet.p5_alerts.model.PersonInfo;
import com.safetynet.p5_alerts.model.PersonInfoResponse;
import com.safetynet.p5_alerts.model.PhoneAlert;
import com.safetynet.p5_alerts.util.EntityNotFoundException;
import com.safetynet.p5_alerts.util.Utils;
/**
 * 
 * MainService implementation
 *
 */
@Service
public class MainServiceImpl implements MainService {

	private static final Logger log = LogManager.getLogger(MainServiceImpl.class);

	@Autowired
	private MedicalRecordDao medicalRecordDao;

	@Autowired
	private FireStationDao fireStationDao;

	@Autowired
	private PersonDao personDao;

	@Override
	public CommunityEmail getCommunityEmails(String city) {
		if (personDao.getCommunityEmails(city).getEmails().isEmpty()) {
			throw new EntityNotFoundException("No emails found for the city: " + city);
		} else {
			return personDao.getCommunityEmails(city);
		}
	}

	@Override
	public PersonInfoResponse personInfo(String lastName, String firstName) {
		log.debug("MainServiceImpl : infos persons for a 'name'");
		PersonInfoResponse PersonInfoResponse = new PersonInfoResponse();
		List<Person> persons = personDao.getAll();
		List<MedicalRecord> medicalRecords = medicalRecordDao.getAll();
		List<PersonInfo> personInfos = new ArrayList<>();
		PersonInfo personInfo;
		for (Person psn : persons) {
			for (MedicalRecord mr : medicalRecords) {
				if (psn.getFirstname().equals(firstName) && psn.getLastname().equals(lastName)
						&& mr.getFirstname().equals(firstName) && mr.getLastname().equals(lastName)) {
					personInfo = new PersonInfo();
					int age = Utils.calculateAgeFromBirthDate(mr.getBirthDate());
					personInfo.setFirstname(psn.getFirstname());
					personInfo.setLastname(psn.getLastname());
					personInfo.setAge(age);
					personInfo.setEmail(psn.getEmail());
					personInfo.setAddress(psn.getAddress());
					personInfo.setAllergies(mr.getAllergies());
					personInfo.setMedications(mr.getMedications());
					personInfos.add(personInfo);
				}
			}
		}
		if (personInfos.isEmpty()) {
			log.info("personInfo : No personInfo found for lastName/firstName");
			throw new EntityNotFoundException("No personInfo found for lastName/firstName: " + lastName + "/"+firstName);
		} else {
			PersonInfoResponse.setPersonInfos(personInfos);
			return PersonInfoResponse;
		}
	}

	@Override
	public ChildAlertResponse childAlert(@RequestParam String address) {
		log.debug(" MainServiceImpl childAlert : list of children from an address");
		// Recherche des enfants
		ChildAlertResponse childAlertResponse = new ChildAlertResponse();
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
						membres.add(psn);
					}
				}
				childAlert.setPersons(membres);
				childAlerts.add(childAlert);
			}
		}
		if (childAlerts.isEmpty()) {
			log.info("childAlert : No childAlert found for address");
			throw new EntityNotFoundException("No childAlert found for address: " + address);
		} else {
			childAlertResponse.setChildAlerts(childAlerts);
			return childAlertResponse;
		}
		
	}

	@Override
	public FirestationPerson firestation(String station) {
		log.debug("MainServiceImpl PersonForFiretationResult : infos persons for a station");
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
		if (!isStationFound) {
			log.info("firestation : No FirestationPerson found for station");
			throw new EntityNotFoundException("No FirestationPerson found for station: " + station);
		} else {
			return firestationPerson;
		}
	}

	// Les telephones des personnes desservies par adresse de la station
	// On dedoublonne
	@Override
	public PhoneAlert phoneAlert(String firestation_number) {
		log.debug("MainServiceImpl phoneAlert : Phone for an address firestation");
		PhoneAlert phoneAlert = new PhoneAlert();
		Set<String> setPhone = new HashSet<>();
		List<String> phones;
		List<Person> persons;
		for (FireStation fireStation : fireStationDao.getAll()) {
			if (fireStation.getStation().equals(firestation_number)) {
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
		if (setPhone.isEmpty()) {
			log.info("phoneAlert : No FirestationPerson found for firestation_number");
			throw new EntityNotFoundException("No FirestationPerson found for firestation_number: " + firestation_number);
		} else {
			return phoneAlert;
		}
	}

	@Override
	// Les personnes a une adresse avec info Firestatione et Medicalrecords
	public PersonForFirestationAddressResponse fire(String address) {
		log.debug("MainServiceImpl fire : Persons infos for a firestation address");
		PersonForFirestationAddressResponse personForFirestationAddressResponse = new PersonForFirestationAddressResponse();
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
			if (medicalRecord != null) {
				age = Utils.calculateAgeFromBirthDate(medicalRecord.getBirthDate());
				personForFirestationAddress.setAge(age);
				personForFirestationAddress.setMedications(medicalRecord.getMedications());
				personForFirestationAddress.setAllergies(medicalRecord.getAllergies());
			}
			lpersonForFirestationAddress.add(personForFirestationAddress);

		}
		if (lpersonForFirestationAddress.isEmpty()) {
			log.info("fire : No PersonForFirestationAddressResponse found for address");
			throw new EntityNotFoundException("No PersonForFirestationAddressResponse found for address: " + address);
		} else {
			personForFirestationAddressResponse.setPersonForFirestationAddress(lpersonForFirestationAddress);
			return personForFirestationAddressResponse;
		}
	}
	
	//
	// FloodStation
	//
	// Chargement dans une Set des adresses des stations (dedoublonées)
		private Set<String> getAdressStations(List<String> stations) {
			log.debug("FloodStationServiceImpl getAdressStations");
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
			log.debug("FloodStationServiceImpl getpersonForFlood");
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
		public HouseholdResponse floodStations(List<String> stations) {
			log.info("floodStations : Households & Persons infos for a firestations list");
			HouseholdResponse householdResponse = new HouseholdResponse();
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
			if (households.isEmpty()) {
				log.info("floodStations : No households found for stations");
				throw new EntityNotFoundException("No households found for stations: " + stations);
			} else {
				householdResponse.setHouseholds(households);
				return householdResponse;
			}

		}

}
