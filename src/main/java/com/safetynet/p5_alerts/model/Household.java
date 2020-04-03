package com.safetynet.p5_alerts.model;

import java.util.List;

/*
 * Model Household
 */
public class Household {
	private String address;
	private List<PersonForFlood> personForFloods;
	
	public Household() {
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<PersonForFlood> getPersonForFloods() {
		return personForFloods;
	}

	public void setPersonForFloods(List<PersonForFlood> personForFloods) {
		this.personForFloods = personForFloods;
	}

	
}
