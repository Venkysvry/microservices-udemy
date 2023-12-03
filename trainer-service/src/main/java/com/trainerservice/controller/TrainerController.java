package com.trainerservice.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerDto;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.entity.WorkingHrsEntity;
import com.trainerservice.exception.TrainerNotFoundException;
import com.trainerservice.serviceImpl.TrainerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trainerdetails")
public class TrainerController {
	@Autowired
	private TrainerServiceImpl impl;

	@PostMapping("/add")
	public ResponseEntity<TrainerEntity> save(@RequestBody @Valid TrainerDto  trainerentity) {
		TrainerEntity saveddata = impl.saveTrainer(trainerentity);
		return ResponseEntity.ok(saveddata);
	} 

	@GetMapping("/getTrainerNdBatch/{coursename}")
	public ResponseEntity<TrainerApiResponse> getTrainerbatch(@PathVariable String coursename) throws ParseException {
		TrainerApiResponse gettrainer = impl.getTrainerBatchList(coursename);
		if (gettrainer.getTrainerentity() == null) {

			throw new TrainerNotFoundException("No one is giving training with the coursse");

		}
		return ResponseEntity.ok(gettrainer);
	}

	@GetMapping("/getByDateRange/{startdate}/{enddate}")
	public ResponseEntity<List<TrainerEntity>> getTrainerDetailsInDateRange(@PathVariable String startdate,
			@PathVariable String enddate) {
		List<TrainerEntity> gettrainer = impl.getListInDateRange(startdate, enddate);
		return ResponseEntity.ok(gettrainer);
	}

	@GetMapping("/noofdays/{start}/{end}")
	public Long getNoDays(@PathVariable String start, @PathVariable String end) {
		try {
			return impl.getNodays(start, end);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/getWorkingHrs/{id}")
	public ResponseEntity<Map<String,String>> getWorkingHrs(@PathVariable String id){
		
		return ResponseEntity.ok(impl.getWorkingHrs(id));
		
	}

	@PutMapping("/updateWorkingHrs/{email}")
	public ResponseEntity<String> updateWorkingHrs(@PathVariable String email,@RequestBody() WorkingHrsEntity hrs){
		return ResponseEntity.ok(impl.updateWorkinghrs(email, hrs));
		
		
	}

}
