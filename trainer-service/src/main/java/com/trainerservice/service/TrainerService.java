package com.trainerservice.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerDto;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.entity.WorkingHrsEntity;

public interface TrainerService {
	TrainerEntity saveTrainer(TrainerDto t);
	
	TrainerApiResponse getTrainerBatchList(String course) throws ParseException;
	List<TrainerEntity> getListInDateRange(String startdate,String enddate);
	
	Map<String,String> getWorkingHrs(String email);
	String updateWorkinghrs(String email,WorkingHrsEntity hrs);
}
