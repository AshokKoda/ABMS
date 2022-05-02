package com.bridgelabz.addressbookcsv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class CSVDemo {

	public static void main(String[] args) {
		System.out.println("************ Welcome to Address Book System ************");

		List<String[]> csvData = createCsvDataSimple();

		try {
			CSVWriter writer = new CSVWriter(new FileWriter("D:/Eclipse_LFP_112/AddressBookManagementSystem/JSONFiles/AddressBook.csv"));
			writer.writeAll(csvData);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static List<String[]> createCsvDataSimple() {
		String[] header = { "id", "name", "address", "phone" };
		String[] record1 = { "1", "first name", "address 1", "11111" };
		String[] record2 = { "2", "second name", "address 2", "22222" };
		
		List<String[]> list = new ArrayList<>();
		list.add(header);
        list.add(record1);
        list.add(record2);
        System.out.println("Added success.!!");
		return list;
	}

}
