package com.bridgelabz.services;

import java.util.Scanner;

public class InputUtil {

	private final static Scanner sc = new Scanner(System.in);

	public static int getIntValue() {
		return sc.nextInt();
	}
	
	public static long getLongValue() {
		return sc.nextLong();
	}

	public static String getStringValue() {
		return sc.next();
	}
}
