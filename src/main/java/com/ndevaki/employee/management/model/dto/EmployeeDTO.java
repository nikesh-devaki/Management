package com.ndevaki.employee.management.model.dto;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.type.LocalDateType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ndevaki.employee.management.model.Employee.Gender;
import com.ndevaki.employee.management.model.Employee.Status;

/*
 * DTO object for employee model object
 */
public class EmployeeDTO {

	
	Long id;
	String firstName;
	String lastName;
	String emailId;
	int age;
	String address;
	Status status;
	Gender gender;
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	LocalDateType joiningDate;
	
	private EmployeeDTO() {
		
	}
	public EmployeeDTO(Long id, String firstName, String lastName, String emailId, int age, String address,
			Status status, Gender gender,LocalDateType joiningDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.age = age;
		this.address = address;
		this.status = status;
		this.gender = gender;
		this.joiningDate=joiningDate;
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
	public LocalDateType getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDateType joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", age=" + age + ", address=" + address + ", status=" + status + ", gender=" + gender + "]";
	}
	
}
