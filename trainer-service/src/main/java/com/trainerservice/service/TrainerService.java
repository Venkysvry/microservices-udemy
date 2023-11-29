package com.trainerservice.service;

import java.time.LocalDate;
import java.util.List;

import com.trainerservice.entity.TrainerEntity;

public interface TrainerService {
	TrainerEntity saveTrainer(TrainerEntity t);
	TrainerEntity getTrainer(String course);
	List<TrainerEntity> getListInDateRange(LocalDate startdate,LocalDate enddate);
}
