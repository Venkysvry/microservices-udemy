package com.studentservice.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentservice.entity.StudentEntity;
import com.studentservice.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rest API service", description = "Crud operations are performed")
@RestController
@RequestMapping("/studentcontroller")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<StudentEntity> addStudent( @RequestBody  StudentEntity user) {
		StudentEntity u = studentService.createUser(user);
		return ResponseEntity.ok().header("student", "venkatesh").body(u);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<StudentEntity> updateStudent(@PathVariable int id, @RequestBody StudentEntity user) {
		user.setId(id);
		StudentEntity u = studentService.updateUser(user);
		return ResponseEntity.ok(u);
	}

	@GetMapping("/findBytech/{id}")
	public ResponseEntity<List<StudentEntity>> getByTech(@PathVariable String id) {
		List<StudentEntity> list = studentService.getStudentsByTechnology(id);
		return ResponseEntity.ok(list);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable String id){
		String s=studentService.deleteStudent(id);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	@GetMapping("/getAllStudents")
	public ResponseEntity<List<StudentEntity>> getAllStudents(){
		List<StudentEntity> list=studentService.getAllStudents();
		return ResponseEntity.ok(list);
	}
	

}
