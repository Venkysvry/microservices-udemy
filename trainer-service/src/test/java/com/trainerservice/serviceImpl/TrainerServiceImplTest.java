package com.trainerservice.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trainerservice.ApiClient.StudentClient;
import com.trainerservice.entity.StudentEntity;
import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerDto;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.entity.WorkingHrsEntity;
import com.trainerservice.exception.HrsModifiedException;
import com.trainerservice.exception.TrainerNotFoundException;
import com.trainerservice.repository.TrainerRepo;
import com.trainerservice.repository.Workinghrsrepo;

@ExtendWith(MockitoExtension.class)
class TrainerServiceImplTest {
	@Mock
	TrainerRepo repo;
	@InjectMocks
	TrainerServiceImpl impl;
	@Mock
	StudentClient client;
	@Mock
	Workinghrsrepo wrepo;

	@Test
	void testSaveTrainer() {
		TrainerDto dto = new TrainerDto("venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00", 2,
				"venkatesh@gmail.com", 12L);
		WorkingHrsEntity w = new WorkingHrsEntity();
		TrainerEntity savingdata = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00",
				"2023-11-29 12:00:00", 2, "venkatesh@gmail.com", w);

		when(repo.save(any())).thenReturn(savingdata);

		TrainerEntity output = impl.saveTrainer(dto);

		assertEquals(savingdata.getEmail(), output.getEmail());
		verify(repo, times(1)).save(any());

	}

	@Test
	void testGetTrainerBatchList() throws ParseException {
		TrainerDto dto = new TrainerDto("venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00", 2,
				"venkatesh@gmail.com", 12L);
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "venkatesh@gmail.com", null);
		StudentEntity std = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		List<StudentEntity> list = new ArrayList<>();
		list.add(std);
		when(repo.findByCourse(any())).thenReturn(trainer);
		when(client.getByTech(any())).thenReturn(list);
		when(repo.save(any())).thenReturn(trainer);
		TrainerApiResponse response = impl.getTrainerBatchList("java");
		assertEquals(dto.getEmail(), response.getTrainerentity().getEmail());
		verify(repo, times(1)).save(any());
		verify(repo, times(1)).findByCourse(any());
	}

	@Test
	void testGetDefaultStudent() throws ParseException {
		TrainerDto dto = new TrainerDto("venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00", 2,
				"venkatesh@gmail.com", 12L);
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "venkatesh@gmail.com", null);
		StudentEntity std = new StudentEntity(1, "venkatesh", "kurva", "venkatesh@gmail.com", "java");
		List<StudentEntity> list = new ArrayList<>();
		list.add(std);
		when(repo.findByCourse(any())).thenReturn(trainer);
		TrainerApiResponse response = impl.getDefaultStudent("java", null);
		assertEquals(dto.getEmail(), response.getTrainerentity().getEmail());

		verify(repo, times(1)).findByCourse(any());

	}

	@Test
	void testGetListInDateRange() {
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "venkatesh@gmail.com", null);
		List<TrainerEntity> list = new ArrayList<>();
		list.add(trainer);
		when(repo.findByStartDate(any())).thenReturn(list);
		List<TrainerEntity> list1 = impl.getListInDateRange(trainer.getStartDate(), trainer.getEndDate());
		assertEquals(1, list1.size());
		verify(repo, times(1)).findByStartDate(any());
	}

	@Test
	void testGetListInDateRange_WithoutStartDate() {
		//List<TrainerEntity> list = new ArrayList<>();
		when(repo.findByStartDate(any())).thenReturn(Collections.emptyList());
		NullPointerException e=assertThrows(NullPointerException.class, ()->impl.getListInDateRange("22", "23"));
		assertNotNull(e);
		verify(repo,times(1)).findByStartDate(any());
	}

	@Test
	void testGetWorkingHrs() {
		List<WorkingHrsEntity> list = new ArrayList<>();
		WorkingHrsEntity w = new WorkingHrsEntity(1L, "22", "gmail.com", "12hrs");
		list.add(w);
		when(wrepo.findByEmail(any())).thenReturn(list);
		Map<String, String> map = impl.getWorkingHrs(w.getEmail());
		assertEquals(1, map.size());
		verify(wrepo, times(1)).findByEmail(any());

	}

	@Test
	void testUpdateWorkinghrs() {
		WorkingHrsEntity w = new WorkingHrsEntity(1L, "22", "gmail.com", "12hrs");
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "gmail.com", w);
		List<WorkingHrsEntity> list = new ArrayList<>();
		
		WorkingHrsEntity hrs = new WorkingHrsEntity(1L, "23", "gmail.com", "12hrs");
		list.add(w);
 
		when(repo.findByEmail("gmail.com")).thenReturn(trainer);
		when(wrepo.findByEmail("gmail.com")).thenReturn(list);
		when(repo.save(any())).thenReturn(trainer);
		String s = impl.updateWorkinghrs("gmail.com", hrs);

		assertEquals("updated successfully", s);
	

	}

	@Test
	void testUpdateWorkinghrs_withoutTrainer() {
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "gmail.com", null);
		List<WorkingHrsEntity> list = new ArrayList<>();
		WorkingHrsEntity w = new WorkingHrsEntity(1L, "22", "gmail.com", "12hrs");
		WorkingHrsEntity hrs = new WorkingHrsEntity(1L, "23", "gmail.com", "12hrs");
		list.add(w);
		when(repo.findByEmail(any())).thenReturn(null);

		TrainerNotFoundException e = assertThrows(TrainerNotFoundException.class,
				() -> impl.updateWorkinghrs("gmail.com", hrs));
		assertNotNull(e);
		verify(repo, times(1)).findByEmail(any());

	}
	@Test
	void testUpdateWorkinghrs_Test1() {
		TrainerEntity trainer = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00",
				2, "gmail.com", null);
		List<WorkingHrsEntity> list = new ArrayList<>();
		WorkingHrsEntity w = new WorkingHrsEntity(1L, "22", "gmail.com", "12hrs");
		WorkingHrsEntity hrs = new WorkingHrsEntity(1L, "22", "gmail.com", "12hrs");
		list.add(w);

		when(repo.findByEmail(any())).thenReturn(trainer);
		when(wrepo.findByEmail(any())).thenReturn(list);
		when(repo.save(any())).thenReturn(trainer);
		HrsModifiedException e = assertThrows(HrsModifiedException.class,
				() -> impl.updateWorkinghrs("gmail.com", hrs));
		assertNotNull(e);
		 
	}
}
