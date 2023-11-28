package com.task.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.departmentservice.entity.DepartmentDto;
import com.task.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	@Autowired
	private DepartmentService impl;
	@PostMapping("/add")
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto dto){
		return new ResponseEntity<>(impl.saveDepartment(dto),HttpStatus.CREATED);
		
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long id){
		DepartmentDto d=impl.getDepartment(id);
		return new ResponseEntity<>(d,HttpStatus.OK);
	}
	@GetMapping("/getByDepartmentCode/{id}")
	public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable String id){
		DepartmentDto d=impl.getDepartmentByCode(id);
		return new ResponseEntity<>(d,HttpStatus.OK);
	}
}
