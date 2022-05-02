package com.bridgelabz.addressbookcsv;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class AddressBook {
	static String name;
	static Scanner sc;
	boolean EXIT = false;
	public HashMap<String, Contact> addressBook = new HashMap<>();
	public List<Contact> listOfContacts = new ArrayList<>();

	public static void main(String[] args) {

		HashMap<String, AddressBook> multiAddressBook = new HashMap<>();
		System.out.println("**************Welcome to the ADDRESS BOOK****************");
		AddressBook ab = new AddressBook();

		AddressBook family = new AddressBook();
		AddressBook friends = new AddressBook();
		AddressBook bussiness = new AddressBook();
		multiAddressBook.put("Family", family);
		multiAddressBook.put("Friends", friends);
		multiAddressBook.put("Business", bussiness);

		ab.createContact(multiAddressBook);

	}

	public void createContact(HashMap<String, AddressBook> multiAddressBook) {
		try {
			CSVWriter familyCsv = new CSVWriter(
					new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Family.csv"));
			CSVWriter friendsCsv = new CSVWriter(
					new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Friends.csv"));
			CSVWriter bussinessCsv = new CSVWriter(
					new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Bussiness.csv"));
			String[] header = { "FirstName", "LastName", "Address", "City", "State", "Zipcode", "PhoneNo", "Email" };
			familyCsv.writeNext(header);
			friendsCsv.writeNext(header);
			bussinessCsv.writeNext(header);

			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			FileWriter file1 = new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Family.json");
			FileWriter file2 = new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Friends.json");
			FileWriter file3 = new FileWriter(
					"D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Bussiness.json");

			JSONArray outerObj1 = new JSONArray();
			JSONArray outerObj2 = new JSONArray();
			JSONArray outerObj3 = new JSONArray();

			JSONObject jsonObject = new JSONObject();

			while (!EXIT) {
				sc = new Scanner(System.in);
				System.out.println("Enter 1 ,2 ,3 for select address book and 4 to exit");
				int option = sc.nextInt();
				String key = "";
				switch (option) {
				case 1:
					key = "Family";
					break;
				case 2:
					key = "Friends";
					break;
				case 3:
					key = "Bussiness";
					break;
				case 4:
					EXIT = true;
				default:
					System.out.println("Invalid option.Please try again.");
				}

				System.out.println("1.Create new contact\n2.Edit Contact\n3.Delete Contact");
				sc = new Scanner(System.in);
				int choice = sc.nextInt();
				if (choice == 1) {
					Contact contact = new Contact();
					contact.setContactInfo();
					name = contact.firstName.toUpperCase() + " " + contact.lastName.toUpperCase();
					if (multiAddressBook.get(key).addressBook.keySet().stream()
							.noneMatch(c -> c.equalsIgnoreCase(name))) {
						multiAddressBook.get(key).addressBook.put(name, contact);
						multiAddressBook.get(key).listOfContacts.add(contact);
						multiAddressBook.get(key).addressBook.get(name).displayContactInfo();

						String csvOutputString = multiAddressBook.get(key).addressBook.get(name).showContactCSV();
						String[] csvData = csvOutputString.split(",");

						String json = gson.toJson(contact);
						JSONParser parser = new JSONParser();
						JSONObject strToJsonObj = (JSONObject) parser.parse(json);

						switch (option) {
						case 1:
							familyCsv.writeNext(csvData);
							outerObj1.add(strToJsonObj);
							break;
						case 2:
							friendsCsv.writeNext(csvData);
							outerObj2.add(strToJsonObj);
							break;
						case 3:
							bussinessCsv.writeNext(csvData);
							outerObj3.add(strToJsonObj);
							break;
						}
					} else {
						System.out.println("Contact already exist duplicate not allowed");
					}
					
					file1.write(outerObj1.toString());
					file2.write(outerObj2.toString());
					file3.write(outerObj3.toString());
			        
					familyCsv.close();
					friendsCsv.close();
					bussinessCsv.close();

					file1.close();
					file2.close();
					file3.close();
				}
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void readJSONFile() throws IOException, ParseException {
		System.out.println("--------------------------------------------------------------\n Read from JSON File: ");
		JSONParser parser = new JSONParser();
		JSONArray jsonArray1 = (JSONArray) parser
				.parse(new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Family.json"));
		System.out.println(jsonArray1.toJSONString());

		JSONArray jsonArray2 = (JSONArray) parser
				.parse(new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Friends.json"));
		System.out.println(jsonArray2.toJSONString());

		JSONArray jsonArray3 = (JSONArray) parser
				.parse(new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Bussiness.json"));
		System.out.println(jsonArray3.toJSONString());
	}

	public void readFromCSVFile() throws CsvValidationException {
		System.out.println("\nReading from CSV files:  \n");
		String[] contactInfo;

		try {
			CSVReader familyCsv = new CSVReader(
					new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Family.csv"));
			while ((contactInfo = familyCsv.readNext()) != null) {
				for (String cell : contactInfo) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
			System.out.println("\n");

			CSVReader friendsCsv = new CSVReader(
					new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Friends.csv"));
			while ((contactInfo = friendsCsv.readNext()) != null) {
				for (String cell : contactInfo) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
			System.out.println("\n");

			CSVReader bussinessCsv = new CSVReader(
					new FileReader("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles.Bussiness.csv"));
			while ((contactInfo = bussinessCsv.readNext()) != null) {
				for (String cell : contactInfo) {
					System.out.print(cell + "\t");
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
