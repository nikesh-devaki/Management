package com.ndevaki.employee.management.model;

import javax.persistence.Entity;

@Entity
public class Address {

	Long id;
	String doorNumber;
	String StreetName;
	String city;
	String state;
	int pinCode;
	
	private Address() {
		
	}
	
	
}
