package com.task.departmentservice.service;

import com.task.departmentservice.entity.DepartmentDto;

public interface DepartmentService {
	DepartmentDto saveDepartment(DepartmentDto d);
	DepartmentDto getDepartment(Long id);
	DepartmentDto getDepartmentByCode(String id);
}
