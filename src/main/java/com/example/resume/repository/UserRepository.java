package com.example.resume.repository;

import org.springframework.stereotype.Repository;

import com.example.resume.model.User;

import org.springframework.data.jpa.repository.*;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
