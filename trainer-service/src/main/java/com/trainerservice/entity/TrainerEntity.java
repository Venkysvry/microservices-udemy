package com.trainerservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "trainerdetails")
public class TrainerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "mentor name should not be empty")

	private String mentorName;

	@Column(nullable = false, unique = true)
	private String course;
	@Column(name = "startDate")
	private String startDate;
	@Column(name = "endDate")
	@NotEmpty(message = "endaate should not be empty")
	private String endDate;
	private int noOfBatches;
	@Email(message = "enter the valid mail")
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "workinghrs", referencedColumnName = "id")
	private WorkingHrsEntity workinghrs;

	public TrainerEntity(Long id, String mentorName, String course, String startDate, String endDate, int noOfBatches,
			String email, WorkingHrsEntity workinghrs) {

		this.id = id;
		this.mentorName = mentorName;
		this.course = course;
		this.startDate = startDate; 
		this.endDate = endDate;
		this.noOfBatches = noOfBatches;
		this.email = email;
		this.workinghrs = workinghrs;
	}

	public TrainerEntity() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNoOfBatches() {
		return noOfBatches;
	}

	public void setNoOfBatches(int noOfBatches) {
		this.noOfBatches = noOfBatches;
	}

	public WorkingHrsEntity getWorkinghrs() {
		return workinghrs;
	}

	public void setWorkinghrs(WorkingHrsEntity workinghrs) {
		this.workinghrs = workinghrs;
	}

}
