package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.TypeLiteral;
import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.FireStationDaoImpl;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;
import com.safetynet.p5_alerts.model.Data;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;

import ch.qos.logback.classic.Logger;

@Service
public class DataServiceImpl implements DataService {

	private static List<Person> persons = new ArrayList<>();
	private static List<FireStation> firestations = new ArrayList<>();
	private static List<MedicalRecord> medicalrecords = new ArrayList<>();
	private String dataRessourceName;

	private PersonDao personDao;
	private FireStationDao fireStationdao;

	Logger log = (Logger) LoggerFactory.getLogger(DataServiceImpl.class);

	public DataServiceImpl(String dataRessourceName) {
		this.dataRessourceName = dataRessourceName;
	}

	public DataServiceImpl() {

	}

	private void loadPerson(Any any) {
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname(any.get("firstName").toString())
				.setLastname(any.get("lastName").toString()).setAddress(any.get("address").toString())
				.setCity(any.get("city").toString()).setZip(any.get("zip").toString())
				.setPhone(any.get("phone").toString()).setEmail(any.get("email").toString()).build();
		persons.add(p);
	}

	private void loadFirestations(Any any) {
		FireStation f = new FireStation();
		f.setAddress(any.get("address").toString());
		f.setStation(any.get("station").toString());
		firestations.add(f);
	}

	private void loadMedicalRecords(Any any) {
		try {
			MedicalRecord m = new MedicalRecord();
			m.setFirstname(any.get("firstName").toString());
			m.setLastname(any.get("lastName").toString());
			Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(any.get("birthdate").toString());
			m.setBirthDate(birthDate);
			List<String> medications = JsonIterator.deserialize(any.get("medications").toString(),
					new TypeLiteral<List<String>>() {
					});
			List<String> allergies = JsonIterator.deserialize(any.get("allergies").toString(),
					new TypeLiteral<List<String>>() {
					});
			m.setMedications(medications);
			m.setAllergies(allergies);
			medicalrecords.add(m);
		} catch (ParseException e) {
			log.error("Error loading loadMedicalRecords", e);
		}
	}

	@Override
	public void loadData() {
		try {
			String data = StreamUtils.copyToString(new ClassPathResource(this.dataRessourceName).getInputStream(),
					Charset.forName("UTF-8"));
			JsonIterator jsoniterator = JsonIterator.parse(data);
			Any any = jsoniterator.readAny();
			Any personAny = any.get("persons");
			Any firestationAny = any.get("firestations");
			Any medicalRecordAny = any.get("medicalrecords");
			personAny.forEach(b -> loadPerson(b));
			firestationAny.forEach(b -> loadFirestations(b));
			medicalRecordAny.forEach(b -> loadMedicalRecords(b));
			System.out.println("persons:" + persons.size());
			System.out.println("firestations:" + firestations.size());
			System.out.println("medicalrecords:" + medicalrecords.size());
			Data inputData = new Data();
			inputData.setPersons(persons);
			inputData.setFireStations(firestations);
			inputData.setMedicalRecords(medicalrecords);
			personDao = new PersonDaoImpl();
			personDao.setPersons(inputData.getPersons());
			fireStationdao = new FireStationDaoImpl();
			fireStationdao.setFireStations(inputData.getFireStation());
			fireStationdao.setFireStations(firestations);
		} catch (IOException e) {
			log.error("Error loading loadData", e);
		}
	}

}
