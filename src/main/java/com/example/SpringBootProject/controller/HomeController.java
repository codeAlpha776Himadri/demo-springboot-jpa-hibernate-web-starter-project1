package com.example.SpringBootProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.SpringBootProject.dao.UserDao;
import com.example.SpringBootProject.model.User;

@Controller
// @ResponseBody // will return string not view 
public class HomeController {

    @Autowired
    private UserDao userDao ; 
    
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView() ; 
        List<User> users = userDao.findAll() ;
        mv.addObject("users",users) ;
        mv.setViewName("home");
        return mv ;
    }

}
