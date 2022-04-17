package com.bridgelabz.controller;

import java.util.Scanner;

import com.bridgelabz.services.AddressBookService;

public class AddressBookMain {

	static Scanner sc;
	
	public static void main(String[] args) {
		System.out.println("************ Welcome to Address Book System ************");
		
		AddressBookService service = new AddressBookService();

		boolean exit = false;
		sc = new Scanner(System.in);
		while (!exit) {
			System.out.println(
					"1.Create Contact \t2.Show Contacts \t3.Update Contact \t0.Quit");
			System.out.println("---------- Enter your option ----------------- ");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				service.addPerson();
				break;
			case 2:
				service.showAllContacts();
				break;
			case 3:
				service.editRecord();
				break;
			case 0:
				exit = true;
				System.out.println("**********Thank you.....!!!*********");
				break;
			default:
				System.out.println("Please Enter Valid Option");
				System.out.println("<------------------------------------------------------->");
			}
		}

	}

}
