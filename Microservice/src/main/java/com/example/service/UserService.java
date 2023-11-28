package com.example.service;

import java.util.List;

import com.example.entity.StudentModel;
import com.example.entity.User;

public interface UserService {

	User createUser(User user);
	User updateUser(User user);
	List<StudentModel> getAllStudents();
}
