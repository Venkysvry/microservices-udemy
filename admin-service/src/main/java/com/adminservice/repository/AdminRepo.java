package com.adminservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adminservice.entity.AdminModel;

public interface AdminRepo extends JpaRepository<AdminModel, Integer> {

}
