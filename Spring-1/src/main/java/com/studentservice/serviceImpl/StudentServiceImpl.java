package com.studentservice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentservice.Exception.ResourceNotFoundException;
import com.studentservice.entity.StudentEntity;
import com.studentservice.repository.StudentRepo;
import com.studentservice.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepo repo;
	// private ModelMapper modelmapper;

	@Override
	public StudentEntity createUser(StudentEntity user) {

		return repo.save(user);
	}

	@Override
	public StudentEntity updateUser(StudentEntity user) {

		StudentEntity u = repo.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("user", "id", user.getId()));
		u.setLastName(user.getLastName());
		u.setFirstName(user.getFirstName());
		u.setEmail(user.getEmail());
		repo.save(u);
		return u;
	}

	@Override
	public List<StudentEntity> getAllStudents() {

		List<StudentEntity> list = repo.findAll();
		if (list.isEmpty()) {
			throw new NullPointerException("list is empty");
		}
		// List<StudentModel> studentlist =
		// user.stream().map(UserServiceImpl::mapToDto).collect(Collectors.toList());
//		List<StudentDto> studentlist = user.stream().map((u) -> modelmapper.map(user, StudentDto.class))
//				.collect(Collectors.toList());
		else {
			return list;
		}
	}

//	public static StudentDto mapToDto(StudentEntity u) {
//		StudentDto s = new StudentDto();
//		s.setId(u.getId());
//		s.setFirstName(u.getFirstName());
//		s.setLastName(u.getLastName());
//		return s;
//	}

	@Override
	public List<StudentEntity> getStudentsByTechnology(String tech) {

		List<StudentEntity> list = repo.findByStudentTechnology(tech);
		if (list.isEmpty()) {
			throw new NullPointerException("there is no data with the given tech");
		} else {
			return list;
		} 
	}

	@Override
	public String deleteStudent(String email) {
		StudentEntity s = repo.findByEmail(email);
		if (s == null) {
			throw new NullPointerException("no data with the given mail");
		}
		repo.delete(s);
		return "deleted student successfully!";
	}
 
	@Override
	public StudentEntity getByEmail(String email) {
		StudentEntity s = repo.findByEmail(email);
		if (s == null) {
			throw new NullPointerException("no data with the given mail");
		}
		return s;
	}

}
