package com.bridgelabz.addressbookcsv;

import java.util.Scanner;

public class Contact {

	public String firstName, lastName, address, city, state, zipcode, phoneNo, email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactInfo() {

		Scanner sc = new Scanner(System.in);
		System.out.print(
				"Enter First Name: \n Last Name: \n Address: \n City: \n State: \n Zipcode: \n PhoneNO: \n Email: \n");
		setFirstName(sc.nextLine());
		setLastName(sc.nextLine());
		setAddress(sc.nextLine());
		setCity(sc.nextLine());
		setState(sc.nextLine());
		setZipcode(sc.nextLine());
		setPhoneNo(sc.nextLine());
		setEmail(sc.nextLine());
	}

	public void displayContactInfo() {
		System.out.print(" First Name: " + firstName + "\n Last Name: " + lastName + "\n Address: " + address
				+ "\n City: " + city + "\n State: " + state + "\n Zipcode: " + zipcode + "\n PhoneNO: " + phoneNo
				+ "\n Email: " + email + "\n");
	}

	public String showContact() {
		return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zipcode + "," + phoneNo
				+ "," + email + "\n";
	}

	public String showContactCSV() {
		return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zipcode + "," + phoneNo
				+ "," + email;
	}

	@Override
	public String toString() {
		return "\"Contact [First Name " + firstName + ", Last Name = " + lastName + "," + " Address = " + address
				+ ", City = " + city + ", State = " + state + ", ZipCode = " + zipcode + ", Phone = " + phoneNo
				+ ", Email = " + email + "]";
	}

}
