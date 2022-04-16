package com.bridgelabz.model;

public class Person {

	private String fname, lname, address, city, state;
	private long phone, zip;

	public Person(String fname, String lname, String address, String city, String state, long phone, long zip) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.zip = zip;
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

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public long getZip() {
		return zip;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Person [fname=" + fname + ", lname=" + lname + ", address=" + address + ", city=" + city + ", state="
				+ state + ", phone=" + phone + ", zip=" + zip + "]";
	}

}
