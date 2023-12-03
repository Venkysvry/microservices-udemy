package com.trainerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainerservice.entity.WorkingHrsEntity;

public interface Workinghrsrepo extends JpaRepository<WorkingHrsEntity, Long> {
	List<WorkingHrsEntity> findByEmail(String email);

}
