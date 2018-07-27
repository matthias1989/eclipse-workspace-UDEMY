package com.luv2code.jackson.json.demo;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Driver {

	public static void main(String[] args) {
		try {
			// create object mapper
			ObjectMapper objectMapper = new ObjectMapper();
			
			// read JSON file (data/sample-lite.json) and map to POJO class
			Student theStudent = objectMapper.readValue(new File("data/sample-full.json"), Student.class);
			
			// print out first name and last name
			System.out.println(theStudent.getFirstName() + " " +theStudent.getLastName());
			
			// print out address
			Address theAddress = theStudent.getAddress();
			System.out.println("The street: "+ theAddress.getStreet());
			System.out.println("The city: "+ theAddress.getCity());
			
			// print out the language
			for(String tempLang : theStudent.getLanguages()) {
				System.out.println("language: "+tempLang);
			}
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}

	}

}
