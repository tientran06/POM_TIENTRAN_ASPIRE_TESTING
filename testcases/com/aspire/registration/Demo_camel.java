package com.aspire.registration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Demo_camel {

	public static void main(String[] args) {
		String a = "bob-love*coding_and#like music";
		String b = "BOB loves-coding";
		String c = "cats AND*Dogs-are Awesome";
		String d = "a b c d-e-f%g";
		String e = "javaSCRIPT is The-BEST";
		String f = "a-b-paper-house";
		String g = "ginger-brea d mAN";

		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(a));
		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(b));
		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(c));
		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(e));
		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(g));
		System.out.println("THE TEXT IS CONVERT INTO: " + convertToCamelCase(d));
		

	}

	public static String convertToCamelCase(String textValue) {
		textValue = removeSpecialCharacter(textValue);
		int space = 0;
		int n = textValue.length();
		char ch[] = textValue.toCharArray();
		int res_ind = 0;
		
		for (int i = 0; i < n; i++) {
		ch[i] = Character.toLowerCase(ch[i]);
		}
		
		for (int i = 0; i < n; i++) {
			
			if (ch[i] == ' ') {
				space++;
				ch[i + 1] = Character.toUpperCase(ch[i + 1]);
			} else {
				ch[res_ind++] = ch[i];
			}
		}
		return String.valueOf(ch, 0, n - space);
	}
	

	public static String removeSpecialCharacter(String textValue) {
		String regex = "[^A-Za-z0-9]";

		// Compile the regex to create pattern
		// using compile() method
		Pattern pattern = Pattern.compile(regex);

		// Get a matcher object from pattern
		Matcher matcher = pattern.matcher(textValue);

		// Replace every matched pattern with the
		// target string using replaceAll() method
		return matcher.replaceAll(" ");
	}

	public static String convertCamel(String textValue) {
		textValue.toString();
		return String.format(textValue, convertCamel(textValue));
	}

}
