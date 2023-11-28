package com.task.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.employeeservice.entity.DepartmentDto;



@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApIClient {
	@GetMapping("/api/departments/getByDepartmentCode/{id}")
	DepartmentDto getDepartmentByCode(@PathVariable String id);
	

}
