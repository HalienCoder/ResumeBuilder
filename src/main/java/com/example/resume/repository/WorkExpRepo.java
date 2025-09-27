package com.example.resume.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.resume.model.User;
import com.example.resume.model.WorkExpAtt;
@Repository
public interface WorkExpRepo extends JpaRepository<WorkExpAtt, Integer>{
	// Defining a method to find work experiences by user
    List<WorkExpAtt> findByUser(User user);
}
