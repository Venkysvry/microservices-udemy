package com.trainerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="workinghrs")
public class WorkingHrsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	 
	private String date;
	private String email;
	private String workinghrs;
	public WorkingHrsEntity() {
		
	}
	public WorkingHrsEntity(Long id, String date,String email, String workinghrs) {
		super();
		this.id = id;
		this.date = date;
		this.workinghrs = workinghrs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkinghrs() {
		return workinghrs;
	}
	public void setWorkinghrs(String workinghrs) {
		this.workinghrs = workinghrs;
	}

}
