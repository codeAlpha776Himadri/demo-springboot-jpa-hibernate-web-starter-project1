package com.example.SpringBootProject.controller;

// import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringBootProject.dao.UserDao;
import com.example.SpringBootProject.model.User;

@RestController
@RequestMapping("/home")
// @ResponseBody // will return string not view 
public class HomeController {

    @Autowired
    private UserDao userDao ; 
    
    @GetMapping("/users")
    public List<User> home() {
        return this.userDao.findAll() ;
    }

    @GetMapping("/admin") 
    public ResponseEntity<Object> getAdminPage() {
        return new ResponseEntity<>("Hey i am admin page!", HttpStatus.OK) ;
    }

}
