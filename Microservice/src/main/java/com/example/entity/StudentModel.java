package com.example.entity;



import jakarta.validation.constraints.NotEmpty;


public class StudentModel {
	private int id;
	@NotEmpty(message="firstname should not be empty")
	private String firstName;
	@NotEmpty(message="lastName should not be empty")
	private String lastName;
	public StudentModel() {
		
	}
	public StudentModel(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "StudentClass [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
