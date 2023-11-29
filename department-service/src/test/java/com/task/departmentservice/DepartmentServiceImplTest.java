package com.task.departmentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.task.departmentservice.entity.Department;
import com.task.departmentservice.entity.DepartmentDto;
import com.task.departmentservice.repository.DepartmentRepo;
import com.task.departmentservice.serviceImpl.DepartmentserviceImpl;
import com.task.departmentservice.serviceImpl.Mapper;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@Mock
	DepartmentRepo repo;
	@Mock
	Mapper mapper;
	@InjectMocks
	DepartmentserviceImpl service;
	Department department;
	DepartmentDto departmentdto;
	Optional<Department> optionaldepartment;
	@BeforeEach
	public void setup() {
		
		departmentdto = new DepartmentDto(1L, "cse", "cse department", "17VD");
		department = new Department(1L, "cse", "cse department", "17VD");
	
	
	}

	@Test
	void testSaveDepartment() {
		DepartmentDto dto = new DepartmentDto();
		when(repo.save(department)).thenReturn(department);	
		when(mapper.mapToentity(departmentdto)).thenReturn(department);
		when(mapper.mapToDto(department)).thenReturn(departmentdto);
		dto = service.saveDepartment(departmentdto);
		assertEquals(department.getId(), dto.getId());
	}

	@Test
	void testGetDepartment() {
		optionaldepartment=Optional.of(new Department(1L, "cse", "cse department", "17VD"));

		when(repo.findById(1l)).thenReturn(optionaldepartment);
	
		DepartmentDto dto=service.getDepartment(departmentdto.getId());
		when(mapper.mapToDto(optionaldepartment.get())).thenReturn(departmentdto);
		assertEquals(optionaldepartment.get().getDepartmentName(), dto.getDepartmentName());
		
	}

	@Test
	void testGetDepartmentByCode() {
		DepartmentDto dbycode=new DepartmentDto();
		when(repo.findByDepartmentCode("17VD")).thenReturn(department);
		when(mapper.mapToDto(department)).thenReturn(departmentdto);
		dbycode=service.getDepartmentByCode("17VD");
		assertEquals(department.getDepartmentName(),dbycode.getDepartmentName());
	}

}
