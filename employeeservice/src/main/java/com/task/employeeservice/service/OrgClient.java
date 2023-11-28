package com.task.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.task.employeeservice.entity.OrganizationDto;



@FeignClient(name="ORGANIZATION-SERVICE")
public interface OrgClient {
	@GetMapping("/api/organization/getOrgByCode/{id}")
	OrganizationDto getBycode(@PathVariable String id);

}
