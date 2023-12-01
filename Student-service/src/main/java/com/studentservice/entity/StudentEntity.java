package com.studentservice.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "student model information")
@Entity
@Table(name = "studentdetails")
public class StudentEntity {
	@Schema(description = "primary key")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Schema(description = "student firstname")
	@NotEmpty(message = "firstname should not be empty")
	private String firstName;

	@Schema(description = "student lastname")
	@NotEmpty(message = "lastname should not be empty")
	private String lastName;

	@Schema(description = "student email")
	@Column(nullable=false ,unique = true)
	@NotEmpty(message = "email should not be empty")
	@Email(message = "email is not vaild")
	private String email;

	@Schema(description = "student technology")	
	@NotEmpty(message = "technology should not be empty")
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
