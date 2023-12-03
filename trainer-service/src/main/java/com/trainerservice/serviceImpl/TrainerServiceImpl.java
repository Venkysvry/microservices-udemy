package com.trainerservice.serviceImpl;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
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
import com.trainerservice.service.TrainerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	TrainerRepo repo;
	@Autowired
	Workinghrsrepo wrepo;
	@Autowired
	StudentClient studentclient;

	@Override
	public TrainerEntity saveTrainer(TrainerDto t) {

		TrainerEntity savingdata = new TrainerEntity();
		savingdata.setId(0l);
		savingdata.setCourse(t.getCourse());
		savingdata.setEmail(t.getEmail());
		savingdata.setEndDate(t.getEndDate());
		savingdata.setStartDate(t.getStartDate());
		savingdata.setMentorName(t.getMentorName());
		savingdata.setNoOfBatches(t.getNoOfBatches());
		savingdata.setWorkinghrs(null);

		repo.save(savingdata);

		return savingdata;
	}

	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultStudent")
	// @Retry(name = "${spring.application.name}", fallbackMethod =
	// "getDefaultStudent")
	@Override
	public TrainerApiResponse getTrainerBatchList(String course) throws ParseException {

		TrainerEntity trainerentity = repo.findByCourse(course);
		TrainerDto dto = new TrainerDto();
		dto.setCourse(trainerentity.getCourse());
		dto.setEmail(trainerentity.getEmail());
		dto.setEndDate(trainerentity.getEndDate());
		dto.setMentorName(trainerentity.getMentorName());
		dto.setNoOfDaysTraining(getNodays(trainerentity.getStartDate(), trainerentity.getEndDate()));
		dto.setStartDate(trainerentity.getStartDate());

		Map<String, List<StudentEntity>> map = new HashMap<>();
		List<StudentEntity> list = studentclient.getByTech(course);
		List<List<StudentEntity>> batchlist = Lists.partition(list, 2);  
		trainerentity.setNoOfBatches(batchlist.size());
		repo.save(trainerentity);
		dto.setNoOfBatches(trainerentity.getNoOfBatches());
		for (int i = 0; i < batchlist.size(); i++) {
			map.put("Batch " + (i + 1), batchlist.get(i));

		}
		TrainerApiResponse trainerresponse = new TrainerApiResponse(dto, map);

		return trainerresponse;

	}

	public TrainerApiResponse getDefaultStudent(String course, Throwable ex) throws ParseException {

		TrainerEntity trainerentity = repo.findByCourse(course);
		TrainerDto dto = new TrainerDto();
		dto.setCourse(trainerentity.getCourse());
		dto.setEmail(trainerentity.getEmail());
		dto.setEndDate(trainerentity.getEndDate()); 
		dto.setMentorName(trainerentity.getMentorName());
		dto.setNoOfBatches(trainerentity.getNoOfBatches());
		dto.setStartDate(trainerentity.getStartDate());
		dto.setNoOfDaysTraining(getNodays(trainerentity.getStartDate(), trainerentity.getEndDate()));

		Map<String, List<StudentEntity>> map = new HashMap<>();
		List<StudentEntity> list = new ArrayList<>();
		list.add(new StudentEntity(1, "rekha", "rendu gajula", "kamesh@gmail.com", "add"));
		map.put("Batch 1", list);
		TrainerApiResponse trainerresponse = new TrainerApiResponse(dto, map);

		return trainerresponse;

	}

	@Override
	public List<TrainerEntity> getListInDateRange(String startdate, String enddate) {
		List<TrainerEntity> list = repo.findByStartDate(startdate);
		if (list.isEmpty()) {
			throw new NullPointerException("No training has started with the given date");
		}
		List<TrainerEntity> list2 = list.stream().filter((l) -> l.getEndDate().equals(enddate)).toList();
		if (list2.isEmpty()) {
			throw new NullPointerException("No training is ending with the given date");
		} 
		return list2; 
	}

	public long getNodays(String dateStart, String dateStop) throws ParseException {
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

	@Override
	public Map<String, String> getWorkingHrs(String email) {
		List<WorkingHrsEntity> whrs = wrepo.findByEmail(email);
		Map<String, String> working = new HashMap<>();
		for (WorkingHrsEntity w : whrs) {
			working.put(w.getDate(), w.getWorkinghrs());
		}
		return working;
	}
 
	@Override
	public String updateWorkinghrs(String email, WorkingHrsEntity hrs) {
		TrainerEntity trainer = repo.findByEmail(email);  
		if (trainer == null) {
			throw new TrainerNotFoundException("No tariner with the given mail " + email);
		}
		List<WorkingHrsEntity> whrs = wrepo.findByEmail(email);
		for (WorkingHrsEntity workingHrsEntity : whrs) {
			if (workingHrsEntity.getEmail()==email && workingHrsEntity.getDate()==hrs.getDate()) {
				throw new HrsModifiedException("you cannot update the working hrs");
			}
		}

		trainer.setWorkinghrs(hrs); 
		repo.save(trainer); 
		return "updated successfully"; 

	}

}
