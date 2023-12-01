package com.adminservice.entity;

import java.time.LocalDate;

public class TrainerEntity {

	private Long id;
	private String mentorName;

	private String course;

	private LocalDate startDate;

	private LocalDate endDate;
	private int noOfBatches;

	public TrainerEntity() {

	}

	public TrainerEntity(Long id, String mentorName, String course, LocalDate startDate, LocalDate endDate,
			int noOfBatches) {
		super();
		this.id = id;
		this.mentorName = mentorName;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.noOfBatches = noOfBatches;

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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getNoOfBatches() {
		return noOfBatches;
	}

	public void setNoOfBatches(int noOfBatches) {
		this.noOfBatches = noOfBatches;
	}

}
