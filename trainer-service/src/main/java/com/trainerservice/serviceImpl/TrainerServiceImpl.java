package com.trainerservice.serviceImpl;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.trainerservice.ApiClient.StudentClient;
import com.trainerservice.entity.StudentEntity;
import com.trainerservice.entity.TrainerApiResponse;
import com.trainerservice.entity.TrainerEntity;
import com.trainerservice.repository.TrainerRepo;
import com.trainerservice.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	TrainerRepo repo;
	@Autowired
	StudentClient studentclient;

	@Override
	public TrainerEntity saveTrainer(TrainerEntity t) {

		TrainerEntity saveddata = repo.save(t);

		return saveddata;
	}

	@Override
	public TrainerApiResponse getTrainer(String course) {

		TrainerEntity trainerentity = repo.findByCourse(course);

		Map<String, List<StudentEntity>> map = new HashMap<>();
		List<StudentEntity> list = studentclient.getByTech(course);
		List<List<StudentEntity>> batchlist = Lists.partition(list, 2);
		trainerentity.setNoOfBatches(batchlist.size());
		for (int i = 0; i < batchlist.size(); i++) {
			map.put("Batch " + (i + 1), batchlist.get(i));

		}
		TrainerApiResponse trainerresponse = new TrainerApiResponse(trainerentity, map);

		return trainerresponse;

	}

	@Override
	public List<TrainerEntity> getListInDateRange(LocalDate startdate, LocalDate enddate) {
		List<TrainerEntity> list = repo.findByStartDate(startdate);
		List<TrainerEntity> list2 = list.stream().filter((l) -> l.getEndDate().equals(enddate)).toList();
		return list2;
	}

	@Override
	public Long getNodaya(String dateStart, String dateStop) throws ParseException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");	
		ZoneId zone = ZoneId.systemDefault();
		ZonedDateTime firstDateTime = LocalDateTime.parse(dateStart, formatter).atZone(zone);
		ZonedDateTime secondDateTime = LocalDateTime.parse(dateStop, formatter).atZone(zone);
		Duration d1 = Duration.between(firstDateTime, secondDateTime);
		long min = d1.toMinutes();
		long hrs = min / 60;
		long days = hrs / 24;

		return days;
	}

}
