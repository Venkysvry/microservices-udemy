package com.trainerservice.ApiClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trainerservice.entity.StudentEntity;


@FeignClient(name ="STUDENT-SERVICE" )
public interface StudentClient {
	@GetMapping("/studentcontroller/findBytech/{id}")
	 List<StudentEntity> getByTech(@PathVariable String id);
}
