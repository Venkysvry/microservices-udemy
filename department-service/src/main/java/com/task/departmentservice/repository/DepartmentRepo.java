package com.task.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.departmentservice.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
Department  findByDepartmentCode(String departmentCode);
}
