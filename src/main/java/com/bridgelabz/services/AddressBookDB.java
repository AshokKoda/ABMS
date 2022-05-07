package com.bridgelabz.services;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class AddressBookDB {

	public static final String JDBC_STR = "jdbc:mysql://localhost:3306/addressbook_system";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "Koda@1996";
	
	private static AddressBookDB addressBookDB;
	private Connection connection;

	public AddressBookDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver class is not found");
			e.printStackTrace();
		}
		listDrivers();
	}

	private void listDrivers() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			System.out.println(driver.getClass().getName());
		}
	}
	
	public static AddressBookDB init() {
		if (addressBookDB == null) {
			addressBookDB = new AddressBookDB();
		}
		return addressBookDB;
	}
	
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(JDBC_STR, USERNAME, PASSWORD);
			System.out.println("Connection is established.");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection can not established.");
			return null;
		}
	}
	
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
