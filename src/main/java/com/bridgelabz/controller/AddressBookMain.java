package com.bridgelabz.controller;

import com.bridgelabz.services.AddressBookService;

public class AddressBookMain {

	public static void main(String[] args) {
		System.out.println("************ Welcome to Address Book System ************");
		
		AddressBookService service = new AddressBookService();
		service.addPerson();

	}

}
