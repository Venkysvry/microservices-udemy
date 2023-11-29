package com.trainerservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainerservice.entity.TrainerEntity;

public interface TrainerRepo extends JpaRepository<TrainerEntity, Long> {
	TrainerEntity findByCourse(String course);
	//List<TrainerEntity> findByStartDatebetweenEntities(LocalDate startDate,LocalDate enddate);
	List<TrainerEntity> findByStartDate(LocalDate startDate);
	

}
