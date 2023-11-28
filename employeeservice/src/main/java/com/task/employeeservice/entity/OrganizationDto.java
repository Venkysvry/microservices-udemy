package com.task.employeeservice.entity;

import java.time.LocalDateTime;

public class OrganizationDto {
	private long id;
	private String organizationName;
	private String organizationDesc;

	private String organizationCode;

	private LocalDateTime createdDate;

	public OrganizationDto() {
		
	}

	public OrganizationDto(long id, String organizationName, String organizationDesc, String organizationCode,
			LocalDateTime createdDate) {
		super();
		this.id = id;
		this.organizationName = organizationName;
		this.organizationDesc = organizationDesc;
		this.organizationCode = organizationCode;
		this.createdDate = createdDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationDesc() {
		return organizationDesc;
	}

	public void setOrganizationDesc(String organizationDesc) {
		this.organizationDesc = organizationDesc;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

}
