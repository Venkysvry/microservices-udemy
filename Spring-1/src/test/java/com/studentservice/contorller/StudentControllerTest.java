package com.studentservice.contorller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentservice.entity.StudentEntity;
import com.studentservice.service.StudentService;

//Run with annoation is used to tell Junit run test cases using the springrunner .class
//webmvc to focus only on the web layer
//for creating dummy bean of the studentservice

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {
	@MockBean
	StudentService studentservice;
	@Autowired
	MockMvc mockmvc;// is used to perform http request and assert responses

	@Test
	void testAddStudent() throws JsonProcessingException, Exception {
		StudentEntity std = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		// when and then return used to tell the functionality of the service methods
		when(studentservice.createUser(std)).thenReturn(std);
		mockmvc.perform(post("/studentcontroller/create").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(std))).andExpect(status().isOk());
		verify(studentservice, times(1)).createUser(any());

	}

	@Test
	void testUpdateStudent() throws JsonProcessingException, Exception {
		StudentEntity std = new StudentEntity(2, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "python");
		std1.setId(2);
		when(studentservice.updateUser(std1)).thenReturn(std);
		mockmvc.perform(put("/studentcontroller/update/{id}", 2).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(std1))).andExpect(status().isOk());
		verify(studentservice, times(1)).updateUser(any());

	}

	@Test
	void testUpdateStudent_notFound() throws JsonProcessingException, Exception {
		int invalidId = 999;
		StudentEntity std = new StudentEntity();
		std.setId(invalidId);
		when(studentservice.updateUser(any())).thenReturn(null);
		mockmvc.perform(put("/studentcontroller/update/{id}", invalidId).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(std))).andExpect(status().isOk());
	}

	@Test
	void testGetByTech() throws JsonProcessingException, Exception {
		List<StudentEntity> list = new ArrayList<>();
		StudentEntity std = new StudentEntity(2, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		list.add(std);
		when(studentservice.getStudentsByTechnology(any())).thenReturn(list);
		mockmvc.perform(get("/studentcontroller/findBytech/{id}", "java").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(list))).andExpect(status().isOk());

		verify(studentservice, times(1)).getStudentsByTechnology(any());

	}

	@Test
	void testGetByTech_without_course() throws JsonProcessingException, Exception {
		when(studentservice.getStudentsByTechnology(any())).thenReturn(Collections.emptyList());
		mockmvc.perform(get("/studentcontroller/findBytech/{id}","java")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(null))).andExpect(status().isOk());
		verify(studentservice,times(1)).getStudentsByTechnology(any());
	}

	@Test
	void testDeleteStudent() throws JsonProcessingException, Exception {
		StudentEntity std = new StudentEntity(2, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		String output = "deleted student successfully!";
		when(studentservice.deleteStudent(any())).thenReturn(output);
		// if you want the resonse from the mockmvc we can use Mockmvc result`
		MvcResult result = mockmvc
				.perform(delete("/studentcontroller/delete/{id}", std.getEmail())
						.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(output)))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result);
		verify(studentservice, times(1)).deleteStudent(any());

	}

	@Test
	void testGetAllStudents() throws JsonProcessingException, Exception {
		List<StudentEntity> list = new ArrayList<>();
		StudentEntity std = new StudentEntity(2, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		when(studentservice.getAllStudents()).thenReturn(list);
		mockmvc.perform(get("/studentcontroller/getAllStudents").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(list))).andExpect(status().isOk());
		verify(studentservice,times(1)).getAllStudents();

	}

	@Test
	void testGetStudentByEMail() throws JsonProcessingException, Exception {
		StudentEntity std = new StudentEntity(2, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		when(studentservice.getByEmail(any())).thenReturn(std);
		mockmvc.perform(get("/studentcontroller/getStudentByEmail/{id}",std.getEmail())
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(std))).andExpect(status().isOk());
		verify(studentservice,times(1)).getByEmail(any());

	}

}
