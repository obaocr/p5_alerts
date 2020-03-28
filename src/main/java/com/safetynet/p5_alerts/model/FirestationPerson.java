package com.safetynet.p5_alerts.model;

import java.util.List;

public class FirestationPerson {

	int nbOfAdult;
	int nbOfChildren;
	private List<PersonForFirestation> personForFirestations;
	
	public FirestationPerson () {
			
		}

	public List<PersonForFirestation> getPersonForFirestations() {
		return personForFirestations;
	}

	public void setPersonForFirestations(List<PersonForFirestation> personForFirestations) {
		this.personForFirestations = personForFirestations;
	}

	public int getNbOfAdult() {
		return nbOfAdult;
	}

	public void setNbOfAdult(int nbOfAdult) {
		this.nbOfAdult = nbOfAdult;
	}

	public int getNbOfChildren() {
		return nbOfChildren;
	}

	public void setNbOfChildren(int nbOfChildren) {
		this.nbOfChildren = nbOfChildren;
	}
}
