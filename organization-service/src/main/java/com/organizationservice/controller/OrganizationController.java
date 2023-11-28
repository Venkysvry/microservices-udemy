package com.organizationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizationservice.entity.OrganizationDto;
import com.organizationservice.service.OrganizationService;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
	@Autowired
	private OrganizationService orgservice;
	
	@PostMapping("/addOrg")
	public ResponseEntity<OrganizationDto> insertOrg(@RequestBody OrganizationDto o){
		OrganizationDto saveduser=orgservice.saveOrganization(o);
		return new ResponseEntity<OrganizationDto>(saveduser,HttpStatus.CREATED);
	}
	@GetMapping("/getOrgByCode/{id}")
	public ResponseEntity<OrganizationDto> getBycode(@PathVariable String id){
		OrganizationDto o=orgservice.getOrganizationByCode(id);
		return new ResponseEntity<OrganizationDto>(o,HttpStatus.OK);
	}

}
