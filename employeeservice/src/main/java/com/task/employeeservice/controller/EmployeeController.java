package com.task.employeeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.employeeservice.entity.ApiResponse;
import com.task.employeeservice.entity.EmployeeDto;
import com.task.employeeservice.service.EmployeeService;
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> insertEmployee(@RequestBody EmployeeDto e){
		EmployeeDto e1=service.saveEmployee(e);
		return new ResponseEntity<>(e1,HttpStatus.CREATED);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<ApiResponse> getEmployee(@PathVariable Long id){
		ApiResponse e1=service.getEmployee(id);
		return new ResponseEntity<>(e1,HttpStatus.OK);
	}

}
