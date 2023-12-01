package com.trainerservice.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerEntity;

public interface TrainerService {
	TrainerEntity saveTrainer(TrainerEntity t);
	TrainerApiResponse getTrainer(String course);
	List<TrainerEntity> getListInDateRange(LocalDate startdate,LocalDate enddate);
	Long getNodaya(String date1,String date2) throws ParseException;
}
