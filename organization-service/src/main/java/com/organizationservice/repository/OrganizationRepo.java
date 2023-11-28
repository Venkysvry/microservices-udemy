package com.organizationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organizationservice.entity.Organization;
@Repository
public interface OrganizationRepo extends JpaRepository<Organization, Long> {
Organization findByOrganizationCode(String code);

}
