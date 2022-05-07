package com.bridgelabz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.model.Person;

public class AddressBookService implements IPerson {

	static AddressBookDB addressBookDb;
	Connection connection;
	public final String INSERT_DATA = "insert into addressBook(firstname, lastname, address, city, state, zip, phoneno, email)values(?,?,?,?,?,?,?,?)";
	public final String FETCH_DATA = "select * from addressbook";
	public final String DELETE_DATA = "delete from addressbook where id =?";

	int id;
	String firstName;
	String fName, lName, address, city, state, zip, phone, email;
	List<Person> personList;
	static Scanner sc;
	public HashMap<String, Person> addressBook = new HashMap<>();

	public AddressBookService() {
		this.personList = new ArrayList<Person>();
		addressBookDb = AddressBookDB.init();
		connection = addressBookDb.getConnection();
	}

	// Add Contact
	public void addPerson() {
		System.out.println("-------------- Add New Contact --------------");
		try {
			PreparedStatement ps = connection.prepareStatement(INSERT_DATA);
			int i = 0;
			sc = new Scanner(System.in);
			while (i == 0) {
				System.out.print("Enter First Name : ");
				String firstName = sc.nextLine();
				if (checkExists(firstName)) {
					System.out.println("Person Name Already Exists!!");
				} else {
					i = 1;
				}
				ps.setString(1, firstName);
			}

			System.out.print("Enter Last Name : ");
			String lastName = InputUtil.getStringValue();
			ps.setString(2, lastName);
			System.out.print("Enter email : ");
			String email = InputUtil.getStringValue();
			ps.setString(8, email);
			System.out.print("Enter Address : ");
			String address = InputUtil.getStringValue();
			ps.setString(3, address);
			System.out.print("Enter city : ");
			String city = InputUtil.getStringValue();
			ps.setString(4, city);
			System.out.print("Enter state : ");
			String state = InputUtil.getStringValue();
			ps.setString(5, state);
			System.out.print("Enter Phone Number : ");
			String phoneNo = InputUtil.getStringValue();
			ps.setString(7, phoneNo);
			System.out.print("Enter zip : ");
			String zipCode = InputUtil.getStringValue();
			ps.setString(6, zipCode);

			personList.add(new Person(id, firstName, lastName, email, address, city, state, phoneNo, zipCode));
			int status = ps.executeUpdate();

			if (status > 0) {
				System.out.println("Added successfully...");
			}else {
				System.out.println("SOmething went wrong...please try again!!!");
			}
			System.out.println("<------------------------------------------------------->");
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	// Edit Contact
	public void editPerson() {
		System.out.println("-------------- Edit Contact --------------");
		try {
			boolean exit = false;
			int i = 0;
			System.out.println("---------- Select field ----------");
			System.out.print("\nEnter #ID to Edit Contact : ");
			sc = new Scanner(System.in);
			int editId = sc.nextInt();

			while (!exit) {
				String UPDATE_DATA = "update addressbook set firstname=?,lastname=?,address=?,city=?,state=?,zip=?,phoneno=?,email=? where id='"
						+ editId + "'";
				PreparedStatement psEdit = connection.prepareStatement(UPDATE_DATA);
				System.out.println(
						"1.First name\n2.Last name\n3.Address\n4.City\n5.State\n6.Zip code\n7.Phone Number\n8.Email\n9.Quit");
				System.out.println("---------- Enter Your Choice ----------");
				int choice = InputUtil.getIntValue();

				sc = new Scanner(System.in);
				switch (choice) {
				case 1:
					while (i == 0) {
						System.out.print("Enter First Name : ");
						fName = sc.nextLine();
						if (checkExists(firstName)) {
							System.out.println("Person Name Already Exists!!");
						} else {
							i = 1;
						}
					}
					break;
				case 2:
					System.out.print("Enter new Lastname : ");
					lName = InputUtil.getStringValue();
					break;
				case 3:
					System.out.print("Enter new Address : ");
					address = InputUtil.getStringValue();
					break;
				case 4:
					System.out.print("Enter new City : ");
					city = InputUtil.getStringValue();
					break;
				case 5:
					System.out.print("Enter new State : ");
					state = InputUtil.getStringValue();
					break;
				case 6:
					System.out.print("Enter new Zip Code : ");
					zip = InputUtil.getStringValue();
					break;
				case 7:
					System.out.print("Enter new Phone : ");
					phone = InputUtil.getStringValue();
					break;
				case 8:
					System.out.print("Enter new Email : ");
					email = InputUtil.getStringValue();
					break;
				case 9:
					exit = true;
					System.out.println("---------- Edit option Quit ----------");
					break;
				default:
					System.out.println("Please Enter Valid Option");
					System.out.println("<------------------------------------------------------->");
				}
				psEdit.setString(1, fName);
				psEdit.setString(2, lName);
				psEdit.setString(3, address);
				psEdit.setString(4, city);
				psEdit.setString(5, state);
				psEdit.setString(6, zip);
				psEdit.setString(7, phone);
				psEdit.setString(8, email);
				int update = psEdit.executeUpdate();

				if (update > 0) {
					System.out.println("Updated Successfully.");
				} else {
					System.out.println("Record Not Found...");
				}
				System.out.println("<------------------------------------------------------->");
			}
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		}

	}

	// Delete Contact
	public void deletePerson() {
		System.out.println("-------------- Delete Contact --------------");
		sc = new Scanner(System.in);
		try {
			PreparedStatement ps = connection.prepareStatement(DELETE_DATA);
			System.out.println("Enter the ID to delete: ");
			int id = sc.nextInt();
			ps.setInt(1, id);
			int delete = ps.executeUpdate();
			if(delete > 0) {
				System.out.println("Contact deleted successfully." + "Delete count: " + delete);
			}else {
				System.out.println("Record Not Found...");
			}
			connection.setAutoCommit(true);
			System.out.println("<------------------------------------------------------->");
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
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
		while (i == 0) {
			System.out.println("----------------Search by City or State--------------");
			System.out.println("1.Search By City \t2.Search By State \t3.Back");
			System.out.println("------------------Choose Your Option-----------------");
			int choice = InputUtil.getIntValue();

			switch (choice) {
			case 1:
				search.searchByCity();
				break;
			case 2:
				search.searchByState();
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

	// sort Address Book by City State Zip
	public void sortAddressBookCityStateZip() {
		sc = new Scanner(System.in);
		SortByCityStateZip sort = new SortByCityStateZip();
		if (personList.isEmpty()) {
			System.out.println("No Records!!!");
		} else {
			System.out.println(
					"Sort By...\n" + "1: First Name\n" + "2: City\n" + "3: State\n" + "4: Zip Code\n" + "5: Back");
			System.out.println("------------- Enter your option -------------");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				sort.sortByName(personList);
				break;
			case 2:
				sort.sortByCity(personList);
				break;
			case 3:
				sort.sortByState(personList);
				break;
			case 4:
				sort.sortByZip(personList);
				break;
			case 5:
				return;
			default:
				System.out.println("Please Enter Valid Option...");
			}
		}

	}

	// showAllContacts
	public void showAllContacts() {
		System.out.println("-------------- Show All Contacts --------------");
		try {
			PreparedStatement ps = connection.prepareStatement(FETCH_DATA);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id"));
				person.setFname(rs.getString("firstname"));
				person.setLname(rs.getString("lastname"));
				person.setAddress(rs.getString("address"));
				person.setCity(rs.getString("city"));
				person.setState(rs.getString("state"));
				person.setZip(rs.getString("zip"));
				person.setPhoneno(rs.getString("phoneno"));
				person.setEmail(rs.getString("email"));
				personList.add(person);

			}
			personList.forEach(a -> {
				System.out.println("ID : " + a.getId());
				System.out.println("Firstname : " + a.getFname());
				System.out.println("Lastname : " + a.getLname());
				System.out.println("Address : " + a.getAddress());
				System.out.println("City : " + a.getCity());
				System.out.println("State : " + a.getState());
				System.out.println("Zip code : " + a.getZip());
				System.out.println("Phone no : " + a.getPhoneno());
				System.out.println("Email : " + a.getEmail());
				System.out.println("<--------------------------------------------------->");
			});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		if (personList.isEmpty()) {
//			System.out.println("No Records To show!!!");
//		} else {
//			Collections.sort(personList, (p1, p2) -> p1.getFname().compareTo(p2.getFname()));
//			for (Person person : personList) {
//				System.out.println(person);
//			}
//		}
	}

}
