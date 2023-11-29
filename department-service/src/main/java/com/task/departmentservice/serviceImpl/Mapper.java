package com.task.departmentservice.serviceImpl;

import org.springframework.stereotype.Service;

import com.task.departmentservice.entity.Department;
import com.task.departmentservice.entity.DepartmentDto;

@Service
public class Mapper {
	public DepartmentDto mapToDto(Department d) {
		DepartmentDto dto = new DepartmentDto(d.getId(), d.getDepartmentName(), d.getDepartmentDescription(),
				d.getDepartmentCode());
		return dto;

	}

	public Department mapToentity(DepartmentDto dto) {
		Department d = new Department(dto.getId(), dto.getDepartmentName(), dto.getDepartmentDescription(),
				dto.getDepartmentCode());
		return d;
	}

}
