package com.example.SpringBootProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.SpringBootProject.dao.UserDao;
import com.example.SpringBootProject.model.User;

@SpringBootApplication
public class DemoSpringBootProject1Application implements CommandLineRunner {
    
    @Autowired
    UserDao userDao ;

    public static void main(String... args) {
        SpringApplication.run(DemoSpringBootProject1Application.class, args) ;
    }



    @Override
    public void run(String... args) throws Exception {
        

        List<User> users = userDao.findAll() ;
        for (User u : users) {
            System.out.println(u);
        }
        
    }

}
