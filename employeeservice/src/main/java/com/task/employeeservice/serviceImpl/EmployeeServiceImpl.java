package com.task.employeeservice.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.task.employeeservice.entity.ApiResponse;
import com.task.employeeservice.entity.DepartmentDto;
import com.task.employeeservice.entity.Employee;
import com.task.employeeservice.entity.EmployeeDto;
import com.task.employeeservice.entity.OrganizationDto;
import com.task.employeeservice.repository.EmployeeRepo;
import com.task.employeeservice.service.ApIClient;
import com.task.employeeservice.service.EmployeeService;
import com.task.employeeservice.service.OrgClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepo repo;
	@Autowired
	ModelMapper mapper;
//	@Autowired
//	RestTemplate restTemplate;
//	@Autowired
//	WebClient webclient;
	@Autowired
	ApIClient apiclient;
	@Autowired
	OrgClient orgclient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto e) {
		Employee employee = mapper.map(e, Employee.class);
		Employee savedEmployee = repo.save(employee);
		return mapper.map(savedEmployee, EmployeeDto.class);
	}
//@CircuitBreaker(name= "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
@Retry(name= "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public ApiResponse getEmployee(Long id) {
		Employee employee = repo.findById(id).get();
		EmployeeDto empdto= mapper.map(employee, EmployeeDto.class);
		//using the resttemplate
		//ResponseEntity<DepartmentDto> d=restTemplate.getForEntity("http://localhost:8080/api/departments/getByDepartmentCode/"+employee.getDepartmentCode(), DepartmentDto.class);
		//ApiResponse apiresponse=new ApiResponse(empdto,d.getBody());
		//using the WebClient
//		DepartmentDto d=webclient
//				.get()
//				.uri("http://localhost:8080/api/departments/getByDepartmentCode/"+employee.getDepartmentCode())
//				.retrieve().bodyToMono(DepartmentDto.class).block();
	DepartmentDto d=apiclient.getDepartmentByCode(employee.getDepartmentCode());
		OrganizationDto o=orgclient.getBycode(employee.getOrganizationCode());
		ApiResponse apiresponse=new ApiResponse(empdto,d,o);
		return apiresponse;
	}
public ApiResponse getDefaultDepartment(Long id,Exception e) {
	Employee employee = repo.findById(id).get();
	EmployeeDto empdto= mapper.map(employee, EmployeeDto.class);
	DepartmentDto d=new DepartmentDto();
	d.setDepartmentName("R&D department");
	d.setDepartmentDescription("reaserch and development");
	d.setDepartmentCode("RD");
	OrganizationDto o=orgclient.getBycode(employee.getOrganizationCode());
	ApiResponse apiresponse=new ApiResponse(empdto,d,o);
	return apiresponse;
}

}
