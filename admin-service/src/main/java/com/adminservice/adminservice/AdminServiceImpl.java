package com.adminservice.adminservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.adminservice.entity.TrainerApiResponse;

@Service
public class AdminServiceImpl {
//	@Autowired
//	TrainerClient trainerclient;
	@Autowired
	WebClient webclient;
	public TrainerApiResponse getTrainingDetails(String tech) {
		TrainerApiResponse trainerresponse=webclient
				.get()
				.uri("http://localhost:7000/trainerdetails/getByCourse/"+tech)
				.retrieve().bodyToMono(TrainerApiResponse.class).block();
		//TrainerApiResponse trainerresponse=trainerclient.getTrainerDetails(tech);
		return trainerresponse;
	}

}
