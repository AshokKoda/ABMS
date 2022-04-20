package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.model.Person;

public class SortByCityStateZip {

	public void sortByName(List<Person> person) {
		person.sort(Person.firstNameSorting);
		person.forEach(System.out::println);
	}

	public void sortByCity(List<Person> person) {
		person.sort(Person.citySorting);
		person.forEach(System.out::println);
	}

	public void sortByState(List<Person> person) {
		person.sort(Person.stateSorting);
		person.forEach(System.out::println);
	}

	public void sortByZip(List<Person> person) {
		person.sort(Person.zipSorting);
		person.forEach(System.out::println);
	}
}
