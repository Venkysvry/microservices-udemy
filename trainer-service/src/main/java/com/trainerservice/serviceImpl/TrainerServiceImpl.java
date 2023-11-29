package com.trainerservice.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.repository.TrainerRepo;
import com.trainerservice.service.TrainerService;
@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	TrainerRepo repo;

	@Override
	public TrainerEntity saveTrainer(TrainerEntity t) {
		TrainerEntity saveddata=repo.save(t);
		return saveddata;
	}

	@Override
	public TrainerEntity getTrainer(String course) {
		System.out.println(course);
		TrainerEntity trainerentity=repo.findByCourse(course);
	
		return trainerentity;

	}

	@Override
	public List<TrainerEntity> getListInDateRange(LocalDate startdate, LocalDate enddate) {
		List<TrainerEntity> list=repo.findByStartDate(startdate);
		List<TrainerEntity> list2=list.stream().filter((l)->l.getEndDate().equals(enddate)).toList();
		return list2;
	}

}
