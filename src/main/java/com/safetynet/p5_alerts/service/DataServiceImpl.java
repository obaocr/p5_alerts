package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.TypeLiteral;
import com.safetynet.p5_alerts.dao.FireStationDao;
import com.safetynet.p5_alerts.dao.FireStationDaoImpl;
import com.safetynet.p5_alerts.dao.MedicalRecordDao;
import com.safetynet.p5_alerts.dao.MedicalRecordDaoImpl;
import com.safetynet.p5_alerts.dao.PersonDao;
import com.safetynet.p5_alerts.dao.PersonDaoImpl;
import com.safetynet.p5_alerts.model.Data;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;

import ch.qos.logback.classic.Logger;

@Component
public class DataServiceImpl implements DataService {

	@Value("${custom.filename}")
	private String filename;

	private static List<Person> persons = new ArrayList<>();
	private static List<FireStation> firestations = new ArrayList<>();
	private static List<MedicalRecord> medicalrecords = new ArrayList<>();
	private String dataRessourceName;

	private PersonDao personDao;
	private FireStationDao fireStationDao;
	private MedicalRecordDao medicalRecordDao;

	Logger log = (Logger) LoggerFactory.getLogger(DataServiceImpl.class);

	public DataServiceImpl(String dataRessourceName) {
		this.dataRessourceName = dataRessourceName;
	}

	public DataServiceImpl() {

	}

	private void loadPerson(Any any) {
		log.debug("DataServiceImpl : loadPerson");
		Person.Builder personlBuilder = new Person.Builder();
		Person p = personlBuilder.setFirstname(any.get("firstName").toString())
				.setLastname(any.get("lastName").toString()).setAddress(any.get("address").toString())
				.setCity(any.get("city").toString()).setZip(any.get("zip").toString())
				.setPhone(any.get("phone").toString()).setEmail(any.get("email").toString()).build();
		persons.add(p);
	}

	private void loadFirestations(Any any) {
		log.debug("DataServiceImpl : loadFirestations");
		FireStation f = new FireStation();
		f.setAddress(any.get("address").toString());
		f.setStation(any.get("station").toString());
		firestations.add(f);
	}

	private void loadMedicalRecords(Any any) {
		try {
			log.debug("DataServiceImpl : loadMedicalRecords");
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
			log.error("DataServiceImpl Error loading loadMedicalRecords", e);
		}
	}

	@Override
	public void loadData() throws IOException {
		try {
			log.debug("DataServiceImpl : loadPerson");
			String pathFile = filename;
			if (filename == null) {
				pathFile = this.dataRessourceName;
			}
			log.info("loadData : debut / this.dataRessourceName / filename /  pathFile : " + this.dataRessourceName
					+ "/" + filename + "/" + pathFile);
			String data = StreamUtils.copyToString(new ClassPathResource(pathFile).getInputStream(),
					Charset.forName("UTF-8"));
			JsonIterator jsoniterator = JsonIterator.parse(data);
			Any any = jsoniterator.readAny();
			Any personAny = any.get("persons");
			Any firestationAny = any.get("firestations");
			Any medicalRecordAny = any.get("medicalrecords");
			personAny.forEach(b -> loadPerson(b));
			firestationAny.forEach(b -> loadFirestations(b));
			medicalRecordAny.forEach(b -> loadMedicalRecords(b));
			Data inputData = new Data();
			inputData.setPersons(persons);
			inputData.setFireStations(firestations);
			inputData.setMedicalRecords(medicalrecords);
			personDao = new PersonDaoImpl();
			personDao.setPersons(inputData.getPersons());
			fireStationDao = new FireStationDaoImpl();
			fireStationDao.setFireStations(inputData.getFireStation());
			medicalRecordDao = new MedicalRecordDaoImpl();
			medicalRecordDao.setMedicalRecords(inputData.getMedicalRecords());
			//
			String logchgt = "loadData / persons:" + persons.size() + " / firestations:" + firestations.size()
					+ " / medicalrecords:" + medicalrecords.size();
			log.info(logchgt);
		} catch (IOException e) {
			log.error("DataServiceImpl Error loading loadData", e);
			throw e;
		}
	}

}
