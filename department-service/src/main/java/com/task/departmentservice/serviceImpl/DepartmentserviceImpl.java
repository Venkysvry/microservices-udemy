package com.task.departmentservice.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.departmentservice.entity.Department;
import com.task.departmentservice.entity.DepartmentDto;
import com.task.departmentservice.repository.DepartmentRepo;
import com.task.departmentservice.service.DepartmentService;

@Service

public class DepartmentserviceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepo repo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto d) {
		Department d1=mapper.map(d,Department.class );
		Department d2 = repo.save(d1);
		return mapper.map(d2, DepartmentDto.class);

	}

	@Override
	public DepartmentDto getDepartment(Long id) {
		Optional<Department> d=repo.findById(id);
		DepartmentDto d1=mapper.map(d.get(),DepartmentDto.class );
		return d1;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String id) {
		Department d=repo.findByDepartmentCode(id);		
		return mapper.map(d,DepartmentDto.class );
	}

}
