package com.task.departmentservice.serviceImpl;

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
//	@Autowired
//	private ModelMapper mapper;
	@Autowired
	private Mapper mapper;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto d) {
		System.out.println(d.getDepartmentDescription());
//		Department d1=mapper.map(d,Department.class );
		Department d1 = mapper.mapToentity(d);
		Department d2 = repo.save(d1);
		return mapper.mapToDto(d2);

	}

	@Override
	public DepartmentDto getDepartment(Long id) {
		Department d = repo.findById(id).get();
		
		//DepartmentDto d1 = mapper.map(d.get(), DepartmentDto.class);
		DepartmentDto d1=mapper.mapToDto(d);
		return d1;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String id) {
		Department d = repo.findByDepartmentCode(id);
	//	return mapper.map(d, DepartmentDto.class);
		return mapper.mapToDto(d);
	}

	
}
