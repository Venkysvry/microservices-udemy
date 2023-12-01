package com.adminservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adminservice.adminservice.AdminServiceImpl;
import com.adminservice.entity.TrainerApiResponse;

@RestController
@RequestMapping("/admincontroller")
public class AdminController {
	@Autowired
	AdminServiceImpl service;	

	@GetMapping("/trainerdetails/{id}")
	public ResponseEntity<TrainerApiResponse> getTrainerDetails(@PathVariable String id){
		TrainerApiResponse trainerresponse=service.getTrainingDetails(id);
		return ResponseEntity.ok(trainerresponse);
		
	}

}
