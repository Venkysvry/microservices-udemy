package com.studentservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.studentservice.Exception.ResourceNotFoundException;
import com.studentservice.entity.StudentEntity;
import com.studentservice.repository.StudentRepo;

@ExtendWith(MockitoExtension.class)
class ServiceImplTest {
	@Mock
	StudentRepo stdrepo;
	@InjectMocks
	StudentServiceImpl impl;
	StudentEntity std;

	@Test
	public void testCreateUser() {
		StudentEntity std = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		when(stdrepo.save(std)).thenReturn(std);
		std = impl.createUser(std);
		assertEquals(std.getEmail(), std.getEmail());

	}

	@Test
	public void testUpdateUser() {
		StudentEntity std = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		when(stdrepo.findById(1)).thenReturn(Optional.of(std));
		when(stdrepo.save(any())).thenReturn(std);
		StudentEntity updateduser = impl.updateUser(std1);
		assertNotNull(updateduser);
		assertEquals(std1.getEmail(), std.getEmail());
		verify(stdrepo, times(1)).findById(1);
		verify(stdrepo, times(1)).save(any());

	}

	@Test
	public void testUpdateUser_WithoutExistingUser() {

		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		when(stdrepo.findById(1)).thenReturn(Optional.empty());
		ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class, () -> impl.updateUser(std1));

		assertNotNull(e);
		verify(stdrepo, times(1)).findById(1);
	}

	@Test
	public void testGetAllStudents() {

		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		List<StudentEntity> list = new ArrayList<StudentEntity>();
		list.add(std1);
		when(stdrepo.findAll()).thenReturn(list);
		List<StudentEntity> list1 = impl.getAllStudents();
		assertEquals(1, list1.size());
		verify(stdrepo, times(1)).findAll();
	}

	@Test
	public void testGetAllStudents_empltyList() {
		
		when(stdrepo.findAll()).thenReturn(Collections.emptyList());
		//here assertThrows is used to verify the method is throwing the NUllpointerexception is thrown or not
		NullPointerException exception = assertThrows(NullPointerException.class, () -> impl.getAllStudents());
		assertNotNull(exception);
		verify(stdrepo,times(1)).findAll();

	}

	@Test
	public void testGetStudentsByTechnology() {
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		List<StudentEntity> list = new ArrayList<>();
		list.add(std1);
		when(stdrepo.findByStudentTechnology(any())).thenReturn(list);
		List<StudentEntity> list1 = impl.getStudentsByTechnology("java");
		assertEquals(1, list1.size());
		verify(stdrepo, times(1)).findByStudentTechnology(any());

	}

	@Test
	public void testGetStudentsByTechnology_withEmptyList() {
		when(stdrepo.findByStudentTechnology(any())).thenReturn(Collections.emptyList());
		NullPointerException e=assertThrows(NullPointerException.class,()->impl.getStudentsByTechnology("java"));
		assertNotNull(e);
		verify(stdrepo,times(1)).findByStudentTechnology("java");
		
	}

	@Test
	public void testDeleteStudent() {
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		when(stdrepo.findByEmail(any())).thenReturn(std1);
		String s = impl.deleteStudent("java");
		assertEquals("deleted student successfully!", s);
		verify(stdrepo, times(1)).findByEmail("java");
		verify(stdrepo, times(1)).delete(std1);

	}
	
	@Test
	public void testDeleteStudent_No_Student() {
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		when(stdrepo.findByEmail(any())).thenReturn(null);
		NullPointerException e=assertThrows(NullPointerException.class, ()->impl.deleteStudent("java"));
		assertNotNull(e);
		verify(stdrepo,times(1)).findByEmail("java");
		
	}
	@Test
	public void testGetByEmail() {
		StudentEntity std1 = new StudentEntity(1, "venkatesh", "kurva", "venkateshkurva@gmail.com", "java");
		when(stdrepo.findByEmail(any())).thenReturn(std1);
		StudentEntity std2=impl.getByEmail(std1.getEmail());
		assertEquals(std1.getEmail(), std2.getEmail());
		verify(stdrepo,times(1)).findByEmail(any());
	}
	@Test
	public void testGetByEmail_without_Student() {
		when(stdrepo.findByEmail(any())).thenReturn(null);
		NullPointerException e=assertThrows(NullPointerException.class, ()->impl.getByEmail("java"));
		assertNotNull(e);
		verify(stdrepo,times(1)).findByEmail(any());
		
	}
}
