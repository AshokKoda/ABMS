package com.bridgelabz.model;

import java.util.Comparator;

public class Person {

	private int id;
	private String fname, lname, address, city, state, zip, phoneno, email;

	public Person() {

	}

	public Person(int id, String fname, String lname, String address, String city, String state, String zip,
			String phoneno, String email) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneno = phoneno;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fname=" + fname + ", lname=" + lname + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", phoneno=" + phoneno + ", email=" + email + "]";
	}

	@Override
	public boolean equals(Object p) {
		return this.fname.equals(((Person) p).getFname());
	}

	@Override
	public int hashCode() {
		return fname.hashCode();
	}

	/* Sort By First Name */
	public static Comparator<Person> firstNameSorting = (p1, p2) -> {
		String firstName = p1.getFname();
		String firstName2 = p2.getFname();
		return firstName.compareToIgnoreCase(firstName2);
	};
	/* Sort By City */
	public static Comparator<Person> citySorting = (p1, p2) -> {
		String city1 = p1.getCity();
		String city2 = p2.getCity();
		return city1.compareToIgnoreCase(city2);
	};
	/* Sort By State */
	public static Comparator<Person> stateSorting = (p1, p2) -> {
		String state1 = p1.getState();
		String state2 = p2.getState();
		return state1.compareToIgnoreCase(state2);
	};
	/* Sort By Zip */
	public static Comparator<Person> zipSorting = (p1, p2) -> {
		String zip1 = p1.getZip();
		String zip2 = p2.getZip();
		return zip1.compareToIgnoreCase(zip2);
	};
}
