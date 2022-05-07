package com.bridgelabz.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.bridgelabz.model.Person;

public class SearchByStateOrCity {

	static Scanner sc;
	List<Person> personList;

	static AddressBookDB addressBookDb;
	Connection connection;

	public SearchByStateOrCity() {
		addressBookDb = AddressBookDB.init();
		connection = addressBookDb.getConnection();
	}

	public void searchByState() {
		int count = 0;
		sc = new Scanner(System.in);
		System.out.println("Enter State to search : ");
		String search = sc.nextLine();

		try {
			System.out.println("List of persons in the required state are: ");
			String SEARCH_BY_STATE = "select * from addressbook where state = '" + search + "'";
			PreparedStatement ps = connection.prepareStatement(SEARCH_BY_STATE);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1));
				System.out.println("Fullname: " + rs.getString(2) + " " + rs.getString(3));
				System.out.println("Address: " + rs.getString(4));
				System.out.println("City: " + rs.getString(5));
				System.out.println("State: " + rs.getString(6));
				System.out.println("Zip code: " + rs.getString(7));
				System.out.println("Phone number: " + rs.getString(8));
				System.out.println("Email-Id: " + rs.getString(9));
				count++;
				System.out.println("<--------------------------------------------------->");
			}
			System.out.println("The number of persons in state : " + count);
			System.out.println("<--------------------------------------------------->");
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	public void searchByCity() {
		int count = 0;
		sc = new Scanner(System.in);
		System.out.println("Enter city to search : ");
		String search = sc.nextLine();
		
		try {
			System.out.println("List of persons in the required city are: ");
			String SEARCH_BY_CITY = "select * from addressbook where city = '" + search + "'";
			PreparedStatement ps = connection.prepareStatement(SEARCH_BY_CITY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt(1));
				System.out.println("Fullname: " + rs.getString(2) + " " + rs.getString(3));
				System.out.println("Address: " + rs.getString(4));
				System.out.println("City: " + rs.getString(5));
				System.out.println("State: " + rs.getString(6));
				System.out.println("Zip code: " + rs.getString(7));
				System.out.println("Phone number: " + rs.getString(8));
				System.out.println("Email-Id: " + rs.getString(9));
				count++;
				System.out.println("<--------------------------------------------------->");
			}
			System.out.println("The number of persons in city : " + count);
			System.out.println("<--------------------------------------------------->");
		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
}
