package com.ndevaki.employee.management.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.type.LocalDateType;


@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    @Column(nullable = false)
	private String firstName;
    @Column(nullable = false)
	private String lastName;
    @Column(nullable = false)
	private String emailId;
    @Column(nullable = false)
	private int age;
    @Column(nullable = false)
	private String address;
    @Column(nullable = false)
	private Status status;
    @Column(nullable = false)
	private Gender gender;
	@Column(nullable=false)
	 private LocalDateType  joiningDate;
	
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

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}
	void setId(Long id) {
		this.id=id;
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
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}	
}
