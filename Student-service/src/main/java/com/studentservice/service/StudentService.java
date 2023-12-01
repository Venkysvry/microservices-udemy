package com.studentservice.service;

import java.util.List;

import com.studentservice.entity.StudentEntity;

public interface StudentService {

	StudentEntity createUser(StudentEntity user);

	StudentEntity updateUser(StudentEntity user);

	List<StudentEntity> getAllStudents();

	List<StudentEntity> getStudentsByTechnology(String tech);

	String deleteStudent(String email);
}
