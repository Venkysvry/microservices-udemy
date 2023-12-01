package com.trainerservice.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.serviceImpl.TrainerServiceImpl;

@RestController
@RequestMapping("/trainerdetails")
public class TrainerController {
	@Autowired
	private TrainerServiceImpl impl;

	@PostMapping("/add")
	public ResponseEntity<TrainerEntity> save(@RequestBody TrainerEntity trainerentity) {
		TrainerEntity saveddata = impl.saveTrainer(trainerentity);
		return ResponseEntity.ok(saveddata);
	}

	@GetMapping("/getByCourse/{coursename}")
	public ResponseEntity<TrainerApiResponse> getTrainerDetails(@PathVariable String coursename) {
		TrainerApiResponse gettrainer = impl.getTrainer(coursename);
		return ResponseEntity.ok(gettrainer);
	}
	@GetMapping("/getByDateRange/{startdate}/{enddate}")
	public ResponseEntity<List<TrainerEntity>> getTrainerDetailsInDateRange(@PathVariable LocalDate startdate,@PathVariable LocalDate enddate) {
		List<TrainerEntity> gettrainer = impl.getListInDateRange(startdate, enddate);
		return ResponseEntity.ok(gettrainer);
	}
	@GetMapping("/noofdays/{start}/{end}")
	public Long getNoDays(@PathVariable String start,@PathVariable String end) {
		try {
			return impl.getNodaya(start, end);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}return null;
	}
	

}
