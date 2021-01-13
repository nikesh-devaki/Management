package com.ndevaki.employee.management.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	Long id;
	String firstName;
	String lastName;
	String emailId;
	int age;
	String address;
	Status status;
	
	public enum Status{
		ACTIVE,
		INACTIVE
	}
	private Employee() {
		
	}
	public enum Gender{
		MALE("Male"),
		FEMALE("Female");
		
		String label;
		
		Gender(String label) {
			this.label=label;
		}
	}
	
	
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
