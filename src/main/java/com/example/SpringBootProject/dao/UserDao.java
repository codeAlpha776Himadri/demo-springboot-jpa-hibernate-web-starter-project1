package com.example.SpringBootProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootProject.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
           
}
