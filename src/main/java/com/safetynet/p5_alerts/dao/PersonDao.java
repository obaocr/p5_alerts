package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.Person;

public interface PersonDao {
	
	// Liste des personnes
	List<Person> getAll();
	
	// Liste des emails des  personnes pour une ville
	List<String> communityEmail(String city);
	
	// Ajouter une personne
	boolean addPerson(Person person);
	
	// Mise  à jour personne
	boolean updatePerson(Person person);
	
	// suppression personne
	boolean deletePerson(Person person);
}
