package com.trainerservice.entity;

public class TrainerDto {
	private String mentorName;

	private String course;

	private String startDate;

	private String endDate;
	private int noOfBatches;
	private long noOfDaysTraining;
	private String email;

	public TrainerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrainerDto(String mentorName, String course, String startDate, String endDate, int noOfBatches,
			String email, long noOfDaysTraining) {
		super();
		this.mentorName = mentorName;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.noOfBatches = noOfBatches;
		this.email = email;
		this.noOfDaysTraining=noOfDaysTraining;
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

	public long getNoOfDaysTraining() {
		return noOfDaysTraining;
	}

	public void setNoOfDaysTraining(long noOfDaysTraining) {
		this.noOfDaysTraining = noOfDaysTraining;
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

	public int getNoOfBatches() {
		return noOfBatches;
	}

	public void setNoOfBatches(int noOfBatches) {
		this.noOfBatches = noOfBatches;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
