package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;


@Repository
public interface StudentRepo extends JpaRepository<User, Integer> {

}
