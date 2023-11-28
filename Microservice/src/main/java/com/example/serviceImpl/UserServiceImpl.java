package com.example.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Exception.ResourceNotFoundException;
import com.example.entity.StudentModel;
import com.example.entity.User;
import com.example.repository.StudentRepo;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private StudentRepo repo;
	private ModelMapper modelmapper;

	@Override
	public User createUser(User user) {

		return repo.save(user);
	}

	@Override
	public User updateUser(User user) {
		User u = repo.findById(user.getId())
				.orElseThrow(()->new ResourceNotFoundException("User", "id", user.getId()));
		u.setLastName(user.getLastName());
		u.setFirstName(user.getFirstName());
		u.setEmail(user.getEmail());
		repo.save(u);
		return u;
	}

	@Override
	public List<StudentModel> getAllStudents() {
		List<User> user = repo.findAll();
		// List<StudentModel> studentlist =
		// user.stream().map(UserServiceImpl::mapToDto).collect(Collectors.toList());
		List<StudentModel> studentlist = user.stream().map((u) -> modelmapper.map(user, StudentModel.class))
				.collect(Collectors.toList());
		return studentlist;
	}

	public static StudentModel mapToDto(User u) {
		StudentModel s = new StudentModel();
		s.setId(u.getId());
		s.setFirstName(u.getFirstName());
		s.setLastName(u.getLastName());
		return s;
	}
	
	
}
