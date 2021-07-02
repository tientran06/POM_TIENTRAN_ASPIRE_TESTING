package com.aspire.registration;

import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;


public class Demo_Regex {

	public static void main(String[] args) {
		String regex = generateRandomRegex();
		System.out.println("THE TEXT INPUT IS: " +regex);
		System.out.println("CHECK THE VALID OF THE TEXT: " +checkTheRegex(regex));
		//checkTheRegex(regex);
		
		System.out.println(Pattern.matches("^([0-9]{8,9}[a-zA-Z]{1})$", "54847582b"));

	}

	public static boolean checkTheRegex(String regex) {
		return regex.matches("^([0-9]{8,9}[a-zA-Z]{1})$");
		
	}
	protected static String generateRandomRegex() {
		String alpha = RandomStringUtils.randomAlphabetic(1);
		 
		if(alpha.matches("[a-z]{1}")) {
			return RandomStringUtils.randomNumeric(8)+alpha;
		}
		else if (alpha.matches("[A-Z]{1}")){
			return RandomStringUtils.randomNumeric(9)+alpha;
		}
		else {
			System.out.println("Please check the text input again");
			return null;
		}
	}
	
}
