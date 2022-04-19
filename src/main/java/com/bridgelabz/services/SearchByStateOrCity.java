package com.bridgelabz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bridgelabz.model.Person;

public class SearchByStateOrCity {

	static Scanner sc;
	List<Person> personList;

	public void searchByState(List<Person> person) {
		int flag = 0;
		int countState = 0;
		sc = new Scanner(System.in);
		List<Person> matches = new ArrayList<Person>();
		System.out.println("Enter State to search : ");
		String search = sc.nextLine();

		for (Person p : person) {
			if (p.getState().equalsIgnoreCase(search)) {
				flag = 1;
				matches.add(p);
			}
		}

		if (flag == 1) {
			System.out.println("List of persons in the required state are: ");
			for (Person p : matches) {
				System.out.println(p);
				countState++;
				System.out.println("The number of persons in state :" + countState);
			}
		} else {
			System.out.println("No persons with is state.!!!");
		}
	}

	public void searchByCity(List<Person> person) {
		int flag = 0;
		int countCity = 0;
		sc = new Scanner(System.in);
		List<Person> matches = new ArrayList<Person>();
		System.out.println("Enter City to search : ");
		String search = sc.nextLine();

		for (Person p : person) {
			if (p.getCity().equalsIgnoreCase(search)) {
				flag = 1;
				matches.add(p);
			}
		}

		if (flag == 1) {
			System.out.println("List of persons in the required city are: ");
			for (Person p : matches) {
				System.out.println(p);
				countCity++;
				System.out.println("The number of persons in city :" + countCity);
			}
		} else {
			System.out.println("No persons with is city.!!!");
		}
	}

}
