package com.trainerservice.entity;

public class StudentEntity {

	private int id;

	private String firstName;

	private String lastName;

	private String email;

	private String studentTechnology;

	public StudentEntity() {

	}

	public StudentEntity(int id, String firstName, String lastName, String email, String studentTechnology) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.studentTechnology = studentTechnology;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStudentTechnology() {
		return studentTechnology;
	}

	public void setStudentTechnology(String studentTechnology) {
		this.studentTechnology = studentTechnology;
	}

}
