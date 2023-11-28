package com.organizationservice.serviceIMpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizationservice.entity.Organization;
import com.organizationservice.entity.OrganizationDto;
import com.organizationservice.repository.OrganizationRepo;
import com.organizationservice.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationRepo repo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public OrganizationDto saveOrganization(OrganizationDto o) {
		Organization input=mapper.map(o, Organization.class);
		Organization saveOrg=repo.save(input);
		return mapper.map(saveOrg, OrganizationDto.class);
	}

	@Override
	public OrganizationDto getOrganizationByCode(String code) {
		Organization o=repo.findByOrganizationCode(code);
		OrganizationDto d=mapper.map(o, OrganizationDto.class);
		return d;
	}

	

}
