package com.safetynet.p5_alerts.model;

public class Person {
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;
	
	public Person () {
		
	}	
	public Person(String firstname, String lastname, String address, String city, String zip, String phone,
			String email) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	public static class Builder {
		private String firstname;
		private String lastname;
		private String address;
		private String city;
		private String zip;
		private String phone;
		private String email;

		public Builder setFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder setLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setZip(String zip) {
			this.zip = zip;
			return this;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public Person build() {
			return new Person(firstname, lastname, address, city, zip, phone, email);
		}
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
