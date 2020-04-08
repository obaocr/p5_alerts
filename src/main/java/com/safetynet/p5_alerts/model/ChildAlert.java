package com.safetynet.p5_alerts.model;

import java.util.List;

/**
 * Model ChildAlert
 */
public class ChildAlert {
	private String firstname;
	private String lastname;
	private int age;
	private List <Person> persons;
	
	public ChildAlert () {
		
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	
	
	
}
