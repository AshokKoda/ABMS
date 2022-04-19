package com.bridgelabz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.bridgelabz.model.Person;

public class AddressBookService implements IPerson {

	String firstName, lastName, email, address, city, state;
	long phoneNo, zipCode;
	List<Person> personList;
	static Scanner sc;

	public AddressBookService() {
		this.personList = new ArrayList<Person>();
	}

	// Add Contact
	public void addPerson() {
		System.out.println("-------------- Add New Contact --------------");
		int i = 0;
		sc = new Scanner(System.in);
		while (i == 0) {
			System.out.print("Enter First Name : ");
			firstName = sc.nextLine();
			if (checkExists(firstName)) {
				System.out.println("Person Name Already Exists!!");
			} else {
				i = 1;
			}
		}
		System.out.print("Enter Last Name : ");
		lastName = InputUtil.getStringValue();
		System.out.print("Enter email : ");
		email = InputUtil.getStringValue();
		System.out.print("Enter Address : ");
		address = InputUtil.getStringValue();
		System.out.print("Enter city : ");
		city = InputUtil.getStringValue();
		System.out.print("Enter state : ");
		state = InputUtil.getStringValue();
		System.out.print("Enter Phone Number : ");
		phoneNo = InputUtil.getLongValue();
		System.out.print("Enter zip : ");
		zipCode = InputUtil.getLongValue();

		personList.add(new Person(firstName, lastName, email, address, city, state, phoneNo, zipCode));
		System.out.println("Contact added successfully...");
	}

	// Edit Contact
	public void editPerson() {
		System.out.println("-------------- Edit Contact --------------");
		int i = 0;
		sc = new Scanner(System.in);

		if (personList.isEmpty()) {
			System.out.println("No Records To edit!!!");
		} else {
			for (Person person : personList) {
				System.out.println("ID: #" + personList.indexOf(person) + " : " + person);
			}
			System.out.print("\nEnter #ID to Edit Contact : ");
			int id = InputUtil.getIntValue();
			System.out.println(personList.get(id));
			System.out.println("<------------------------------------------------------->");

			boolean exit = false;
			while (!exit) {
				System.out.println("---------- Select field ----------");
				System.out.println(
						"1.First name \t2.Last name \t3.Email \t4.Address \t5.City \t6.State \t7.Phone Number \t8.Zip code \t9.Quit");
				System.out.println("---------- Enter Your Choice ----------");
				int choice = InputUtil.getIntValue();

				switch (choice) {
				case 1:
					while (i == 0) {
						System.out.print("Enter First Name : ");
						firstName = sc.nextLine();
						if (checkExists(firstName)) {
							System.out.println("Person Name Already Exists!!");
						} else {
							i = 1;
						}
					}
					personList.get(id).setFname(firstName);
					break;
				case 2:
					System.out.print("Enter new Lastname : ");
					lastName = InputUtil.getStringValue();
					personList.get(id).setLname(lastName);
					break;
				case 3:
					System.out.print("Enter new Email : ");
					email = InputUtil.getStringValue();
					personList.get(id).setEmail(email);
					break;
				case 4:
					System.out.print("Enter new Address : ");
					address = InputUtil.getStringValue();
					personList.get(id).setAddress(address);
					break;
				case 5:
					System.out.print("Enter new City : ");
					city = InputUtil.getStringValue();
					personList.get(id).setCity(city);
					break;
				case 6:
					System.out.print("Enter new State : ");
					state = InputUtil.getStringValue();
					personList.get(id).setState(state);
					break;
				case 7:
					System.out.print("Enter new Phone : ");
					phoneNo = InputUtil.getLongValue();
					personList.get(id).setPhone(phoneNo);
					break;
				case 8:
					System.out.print("Enter new Zip Code : ");
					zipCode = InputUtil.getLongValue();
					personList.get(id).setZip(zipCode);
					break;
				case 9:
					exit = true;
					System.out.println("---------- Edit option Quit ----------");
					break;
				default:
					System.out.println("Please Enter Valid Option");
				}
				System.out.println(personList.get(id));
				System.out.println("Contact is updated successfully.!!!");
			}
		}

	}

	// Delete Contact
	public void deletePerson() {
		System.out.println("-------------- Delete Contact --------------");
		if (personList.isEmpty()) {
			System.out.println("No Records To delete!!!");
		} else {
			for (Person person : personList) {
				System.out.println("ID: #" + personList.indexOf(person) + " : " + person);
			}

			System.out.print("\nEnter #ID to delete contact : ");
			int id = InputUtil.getIntValue();
			personList.remove(id);
			System.out.println("Contact is deleted successfully.........!!!!!!");
		}

	}

	// check duplicate entry
	public boolean checkExists(String firstName) {
		int flag = personList.stream().anyMatch(p -> p.getFname().equalsIgnoreCase(firstName)) ? 1 : 0;
		return flag == 1;
	}

	// search contacts by city or state
	public void searchInContacts() {
		int i = 0;
		SearchByStateOrCity search = new SearchByStateOrCity();
		if (personList.isEmpty()) {
			System.out.println("No Records To search!!!");
		} else {
			while (i == 0) {
				System.out.println("----------------Search by City or State--------------");
				System.out.println("1.Search By City \t2.Search By State \t3.Back");
				System.out.println("------------------Choose Your Option-----------------");
				int choice = InputUtil.getIntValue();

				switch (choice) {
				case 1:
					search.searchByCity(personList);
					break;
				case 2:
					search.searchByState(personList);
					break;
				case 3:
					i = 1;
					System.out.println("Search Quit");
					break;
				default:
					System.out.println("Please Enter Valid Option.!!!");
				}
			}
		}
	}

	// viewContactsByCityOrState
	public void viewContactsByCityOrState() {
		if (personList.isEmpty()) {
			System.out.println("No Records!!!");
		} else {
			System.out.println("Enter city name: ");
			String city = sc.next();
			System.out.println("Enter state name");
			String state = sc.next();

			for (Person person : personList) {
				if (city.equalsIgnoreCase(person.getCity()) || state.equalsIgnoreCase(person.getState())) {
					System.out.println("View persons contact by searching city or state :");
					System.out.println(person);
				} else {
					System.out.println("No such a records in adddressbook");
				}
			}
		}

	}

	// showAllContacts
	public void showAllContacts() {
		System.out.println("-------------- Show All Contacts --------------");
		if (personList.isEmpty()) {
			System.out.println("No Records To show!!!");
		} else {
			for (Person person : personList) {
				System.out.println(person);
			}
		}
	}

}
