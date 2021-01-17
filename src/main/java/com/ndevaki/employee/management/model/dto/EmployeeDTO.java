package com.ndevaki.employee.management.model.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.ndevaki.employee.management.model.Employee.Gender;
import com.ndevaki.employee.management.model.Employee.Status;

/*
 * DTO object for employee model object
 */
public class EmployeeDTO {

	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	String firstName;
	String lastName;
	String emailId;
	int age;
	String address;
	Status status;
	Gender gender;
	
	
	public EmployeeDTO(Long id, String firstName, String lastName, String emailId, int age, String address,
			Status status, Gender gender) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.age = age;
		this.address = address;
		this.status = status;
		this.gender = gender;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", age=" + age + ", address=" + address + ", status=" + status + ", gender=" + gender + "]";
	}
	
}
