package com.example.SpringBootProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootProject.model.User;

// @Repository   -- not required as this is interface , dont use stereotype annotations at the top of abstract class or interface
public interface UserDao extends JpaRepository<User, Integer> {
           
}
