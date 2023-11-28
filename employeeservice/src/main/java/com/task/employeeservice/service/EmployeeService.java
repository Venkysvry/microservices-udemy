package com.task.employeeservice.service;

import com.task.employeeservice.entity.ApiResponse;
import com.task.employeeservice.entity.EmployeeDto;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto e);
	ApiResponse getEmployee(Long id);
}
