package com.bridgelabz.controller;

import java.util.HashMap;
import java.util.Scanner;

import com.bridgelabz.services.AddressBookService;
import com.bridgelabz.services.IPerson;

public class AddressBookMain {

	static Scanner sc;

	public static void main(String[] args) {
		System.out.println("************ Welcome to Address Book System ************");
		sc = new Scanner(System.in);

		HashMap<String, IPerson> addressBooks = new HashMap<String, IPerson>();
		IPerson familyAddressBook = new AddressBookService();
		IPerson friendAddressBook = new AddressBookService();
		IPerson businessAddressBook = new AddressBookService();
		addressBooks.put("Family", familyAddressBook);
		addressBooks.put("Friend", friendAddressBook);
		addressBooks.put("Business", businessAddressBook);

		int userOption = 0;
		while (userOption != 5) {
			userOption = getOptions(sc);
			String inputAddressBook = "";
			if (userOption != 2) {
				sc = new Scanner(System.in);
				System.out.println("----------------- Select the Addressbook --------------------");
				System.out.println("Family \nFriend \nBusiness \nPress any key and click enter to exit.");
				inputAddressBook = sc.nextLine();
				
			}

			try {
				IPerson ab = addressBooks.get(inputAddressBook);
				switch (userOption) {
				case 1:
					ab.addPerson();
					break;
				case 2:
					addressBooks.forEach((key, addressBook) -> {
						System.out.println("------------" + key + "------------");
						addressBook.showAllContacts();
					});
					break;
				case 3:
					ab.editPerson();
					break;
				case 4:
					ab.deletePerson();
					break;
				case 5:
					ab.searchInContacts();
					break;
				case 6:
					userOption = 5;
					System.out.println("**********Thank you.....!!!*********");
					break;
				default:
					System.out.println("Please Enter Valid Option");
					System.out.println("<------------------------------------------------------->");
				}
			} catch (NullPointerException e) {
				System.out.println("Address book is not found with " + inputAddressBook + " name.");
			}
		}

	}

	public static int getOptions(Scanner sc) {
		System.out.println("**********************Select Menu**********************");
		System.out.println("1.Add Contact \t2.Show Contacts \t3.Edit Contact \t4.Delete Contact \t5.Search By City or State \t6.Exit");
		System.out.println("---------- Enter Your Choice ----------");
		int option = sc.nextInt();
		return option;
	}

}
