package com.safetynet.p5_alerts.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.spi.TypeLiteral;
import com.safetynet.p5_alerts.model.FireStation;
import com.safetynet.p5_alerts.model.MedicalRecord;
import com.safetynet.p5_alerts.model.Person;

@Service
public class DataServiceImpl implements DataService {

	private static List<Person> persons = new ArrayList<>();
	private static List<FireStation> firestations = new ArrayList<>();
	private static List<MedicalRecord> medicalrecords = new ArrayList<>();
	private String dataRessourceName;

	public DataServiceImpl(String dataRessourceName) {
		this.dataRessourceName = dataRessourceName;
	}

	public DataServiceImpl() {

	}

	private void loadPerson(Any any) {
		Person p = new Person();
		p.setFirstname(any.get("firstName").toString());
		p.setLastname(any.get("lastName").toString());
		p.setAddress(any.get("address").toString());
		p.setCity(any.get("city").toString());
		p.setZip(any.get("zip").toString());
		p.setPhone(any.get("phone").toString());
		p.setEmail(any.get("email").toString());
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
			// logger à gérer
		}
	}

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
		} catch (IOException e) {
			// logger à gérer
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public List<FireStation> getFirestations() {
		return firestations;
	}

	public List<MedicalRecord> getMedicalrecords() {
		return medicalrecords;
	}

	public void savePerson(Person person) {
		persons.add(person);
	}

	public void updatePerson(Person person) {
		int pos = 0;
		for (Person p : persons) {
			if (p.getFirstname().equals(person.getFirstname()) && p.getLastname().equals(person.getLastname())) {
				persons.set(pos, person);
			}
			pos++;
		}
		
	}
	
	public void deletePerson(Person person) {
		Iterator<Person> i = persons.iterator();
		while (i.hasNext()) {
		   Person o = i.next();
		   if (o.getFirstname().equals(person.getFirstname()) && o.getLastname().equals(person.getLastname())) {
				i.remove();
			}
		}
	}

}
