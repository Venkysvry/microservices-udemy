package com.task.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.employeeservice.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
