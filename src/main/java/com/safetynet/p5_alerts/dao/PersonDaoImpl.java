package com.safetynet.p5_alerts.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.safetynet.p5_alerts.model.Person;
import com.safetynet.p5_alerts.service.DataService;
import com.safetynet.p5_alerts.service.DataServiceImpl;

/**
 * @author S063912
 *
 */
@Component
public class PersonDaoImpl implements PersonDao {

	// Retourne toutes les personnes
	@Override
	public List<Person> getAll() {
		DataService dataServiceImpl = new DataServiceImpl();
		return dataServiceImpl.getPersons();
	}

	// Retourne une liste d'emails pour les personnes habitants dans une ville
	@Override
	public List<String> communityEmail(String city) {
		List<String> emails = new ArrayList<>();

		// faire de l'injection par la suite
		DataService dataServiceImpl = new DataServiceImpl();
		List<Person> persons = dataServiceImpl.getPersons();
		System.out.println("Dans PersonDaoImpl.communityEmaiTtes, persons.size : " + persons.size());
		for (Person person : persons) {
			// !!! mettre equals pour tester la valeur sinon KO !!!
			if (person.getCity().equals(city)) {
				emails.add(person.getEmail());
			}
		}
		return emails;
	}

	// Ajout personne
	@Override
	public boolean addPerson(Person person) {
		DataService dataServiceImpl = new DataServiceImpl();
		dataServiceImpl.savePerson(person);
		return true;
	}

	// Mise a jour personne
	@Override
	public boolean updatePerson(Person person) {
		DataService dataServiceImpl = new DataServiceImpl();
		dataServiceImpl.updatePerson(person);
		return true;
	}
	
	// Mise a jour personne
		@Override
		public boolean deletePerson(Person person) {
			DataService dataServiceImpl = new DataServiceImpl();
			dataServiceImpl.deletePerson(person);
			return true;
		}
}
