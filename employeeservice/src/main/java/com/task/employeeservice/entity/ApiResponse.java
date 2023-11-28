package com.task.employeeservice.entity;

public class ApiResponse {
	private EmployeeDto empDto;
	private DepartmentDto deptDto;
	private OrganizationDto orgDto;
	public ApiResponse() {
		
	}
	public ApiResponse(EmployeeDto empDto, DepartmentDto deptDto,OrganizationDto orgDto) {
		
		this.empDto = empDto;
		this.deptDto = deptDto;
		this.orgDto=orgDto;
	}
	public OrganizationDto getOrgDto() {
		return orgDto;
	}
	public void setOrgDto(OrganizationDto orgDto) {
		this.orgDto = orgDto;
	}
	public EmployeeDto getEmpDto() {
		return empDto;
	}
	public void setEmpDto(EmployeeDto empDto) {
		this.empDto = empDto;
	}
	public DepartmentDto getDeptDto() {
		return deptDto;
	}
	public void setDeptDto(DepartmentDto deptDto) {
		this.deptDto = deptDto;
	}
	

}
