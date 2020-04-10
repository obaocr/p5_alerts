package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;
/**
 * 
 * DataService to load data
 *
 */
@Component
public class DataServiceImpl implements DataService {

	private static final Logger log = LogManager.getLogger(DataServiceImpl.class);
	
	@Value("${custom.filename}")
	private String filename;
	
	private List<Person> persons = new ArrayList<>();
	private List<FireStation> firestations = new ArrayList<>();
	private List<MedicalRecord> medicalrecords = new ArrayList<>();
	
	private PersonDao personDao;
	private FireStationDao fireStationDao;
	private MedicalRecordDao medicalRecordDao;

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
			firestations.clear();
			persons.clear();
			medicalrecords.clear();
			log.debug("DataServiceImpl : loadPerson");
			String pathFile = filename;
			log.info("loadData : debut / filename /  pathFile : " + "/" + filename + "/" + pathFile);
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
			personDao = new PersonDaoImpl(persons);
 			fireStationDao = new FireStationDaoImpl(firestations);
			medicalRecordDao = new MedicalRecordDaoImpl(medicalrecords);
			//
			String logchgt = "loadData / persons:" + persons.size() + " / firestations:" + firestations.size()
					+ " / medicalrecords:" + medicalrecords.size();
			String logchgtDao = "loadData / personDao:" + personDao.getAll().size() + " / fireStationDao:" + fireStationDao.getAll().size()
			+ " / medicalRecordDao:" + medicalRecordDao.getAll().size();
			log.info(logchgt);
			log.info(logchgtDao);
		} catch (IOException e) {
			log.error("DataServiceImpl Error loading loadData", e);
			throw e;
		}
	}

}
