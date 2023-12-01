package com.adminservice.adminservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.adminservice.entity.TrainerApiResponse;



@FeignClient(url = "http://localhost:7000",name="TRAINER-SERVICE")
public interface TrainerClient {
	@GetMapping("/trainerdetails/getByCourse/{coursename}")
	public TrainerApiResponse getTrainerDetails(@PathVariable String coursename); 

}
