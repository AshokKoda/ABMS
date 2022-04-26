package com.bridgelabz.ReadAndWriteIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddressBook {

	static Scanner sc;
	static String path = "D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles/AddressBook.json";
	List<Contact> contactList;
	
	public AddressBook() {
		contactList = new ArrayList<>();
	}

	enum IOStream {
		Json_File_Name(path);

		final String fileName;

		private IOStream(String fileName) {
			this.fileName = fileName;
		}

		public String getConstant() {
			return fileName;
		}
	}

	public void createFileAndAddContacts() {
		AddressBook addressBook = new AddressBook();
		File file = new File(IOStream.Json_File_Name.getConstant());
		try {
			if (file.createNewFile()) {
				System.out.println("New file is added successfully!!!");
				addressBook.addContacts();
			} else {
				System.out.println("Data is adding an existing file...");
				addressBook.addContacts();
			}
			System.out.println("JSON Data Read: ");
			readJsonFile();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void addContacts() {
		System.out.println("---------------------Add Employee--------------------");
		sc = new Scanner(System.in);
		Contact contact = new Contact();
		int id = (int) (Math.random() * 900 + 100);
		contact.setId(id);
		System.out.print("Enter First Name : ");
		String fName = sc.nextLine();
		contact.setFname(fName);
		System.out.print("Enter Last Name : ");
		String lName = sc.nextLine();
		contact.setLname(lName);
		System.out.print("Enter email : ");
		String email = sc.nextLine();
		contact.setEmail(email);
		System.out.print("Enter Address : ");
		String address = sc.nextLine();
		contact.setAddress(address);
		System.out.print("Enter city : ");
		String city = sc.nextLine();
		contact.setCity(city);
		System.out.print("Enter state : ");
		String state = sc.nextLine();
		contact.setState(state);
		System.out.print("Enter zip : ");
		String zip = sc.nextLine();
		contact.setZip(zip);
		System.out.print("Enter Phone Number : ");
		long phone = sc.nextLong();
		contact.setPhone(phone);
		contactList.add(contact);
		
		System.out.println("JSON Data Write: ");
		writeJsonFile();
		System.out.println("Added Success!!!");
		System.out.println("--------------------------------------------------------------");
	}
	
	public void writeJsonFile() {
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(contactList);
			System.out.println("Json data: " + jsonString);
			FileWriter writer = new FileWriter(IOStream.Json_File_Name.getConstant(), true);
			writer.write(jsonString);
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void readJsonFile() {
		String path = "D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles/AddressBook.json";
		try {
			List<String> lines = Files.readAllLines(Paths.get(path),
			        StandardCharsets.UTF_8);
			lines.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("************ Welcome to Address Book System ************");

		AddressBook service = new AddressBook();
		boolean exit = false;
		while (!exit) {
			System.out.println("<------------------------------------------------------->");
			System.out.println("1. Add Contacts \t0.Exit");
			System.out.println("<-----------------Choose your options----------------->");
			sc = new Scanner(System.in);
			int option = sc.nextInt();
			System.out.println("<------------------------------------------------------->");
			switch (option) {
			case 1:
				service.createFileAndAddContacts();
				break;
			case 0:
				exit = true;
				System.out.println("***************Thank you***************");
				break;
			default:
				System.out.println("Invalid option.Please try again.!!!");
			}
		}
	}
}
