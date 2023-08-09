package com.example.SpringBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringBootProject.dao.UserDao;
import com.example.SpringBootProject.model.User;

@Controller
// @ResponseBody // will return string not view 
public class HomeController {

    @Autowired
    private UserDao userDao ; 
    
    @RequestMapping("/")
    public String home(Model m) {
        List<User> users = userDao.findAll() ;
        m.addAttribute("users",users) ;
        return "home" ;
    }

}
