package com.safetynet.p5_alerts.dao;

import java.util.List;

import com.safetynet.p5_alerts.model.CommunityEmail;
import com.safetynet.p5_alerts.model.Person;


public interface PersonDao {
	
	// Alimentation des données initiales
	void setPersons(List<Person> persons);
	
	// Liste des personnes
	public List<Person> getAll();
	
	// Recherche par nom/prenom
	public Person searchByName(String lastName, String firstName);
	
	// Recherche par nom/prenom
	public List<Person> searchByAddress(String address);
	
	 //Liste des emails des  personnes pour une ville
	public CommunityEmail getCommunityEmails(String city);
	
	// Ajouter une personne
	public boolean addPerson(Person person);
	
	// Mise  à jour personne
	public boolean updatePerson(Person person);
	
	// suppression personne
	public boolean deletePerson(Person person);
	
	// suppression de toutes les personnes
	public boolean deleteAll();
}
