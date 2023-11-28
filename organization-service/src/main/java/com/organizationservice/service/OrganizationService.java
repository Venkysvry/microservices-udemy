package com.organizationservice.service;

import com.organizationservice.entity.OrganizationDto;

public interface OrganizationService {
	OrganizationDto saveOrganization(OrganizationDto o);
	OrganizationDto getOrganizationByCode(String code);

}
