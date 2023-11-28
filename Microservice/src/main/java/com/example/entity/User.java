package com.example.entity;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "user model information")
@Entity
@Table(name="studentdetails")
public class User {
	@Schema(description = "primary key")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Schema(description = "user firstname")
	@NotEmpty(message="firstname should not be empty")
	private String firstName;
	
	@Schema(description = "user lastname")
	@NotEmpty(message="lastname should not be empty")
	private String lastName;
	
	@Schema(description = "user email")
	@NotEmpty(message="email should not be empty")
	@Email(message="email is not vaild")
	private String email;
	public User() {
		
	}
	public User(int id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
	
}
