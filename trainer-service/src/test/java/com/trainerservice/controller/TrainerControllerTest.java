package com.trainerservice.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainerservice.entity.TrainerDto;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.entity.WorkingHrsEntity;
import com.trainerservice.serviceImpl.TrainerServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainerController.class)
class TrainerControllerTest {
	@MockBean
	TrainerServiceImpl service;
	@Autowired
	MockMvc mvc;

	@Test
	void testSave() throws JsonProcessingException, Exception {
		TrainerDto dto = new TrainerDto("venkatesh", "java", "2022-11-30 12:00:00", "2023-11-29 12:00:00", 2,
				"venkatesh@gmail.com", 12L);
		WorkingHrsEntity w = new WorkingHrsEntity();
		TrainerEntity savingdata = new TrainerEntity(1l, "venkatesh", "java", "2022-11-30 12:00:00",
				"2023-11-29 12:00:00", 2, "venkatesh@gmail.com", w);
		when(service.saveTrainer(dto)).thenReturn(savingdata);
		mvc.perform(post("/trainerdetails/add").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(savingdata))).andExpect(status().isOk());

		verify(service, times(1)).saveTrainer(any());
	}

}
