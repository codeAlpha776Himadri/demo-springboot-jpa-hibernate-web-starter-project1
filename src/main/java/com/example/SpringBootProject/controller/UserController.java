package com.example.SpringBootProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootProject.model.SecuredUser;
import com.example.SpringBootProject.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService ; 


    @GetMapping("/all") 
    public List<SecuredUser> fetchUsers() {
        return this.userService.fetchUsers() ;
    }

    
    @PostMapping(value = "/add")
    /*  @RequestBody - used to get body parameters from the request */
    public ResponseEntity<Object> addUser(@RequestBody SecuredUser user) {
        Map<String, List<SecuredUser>> data = new HashMap<>()  ;
        this.userService.addUser(user) ;
        data.put("users", this.userService.fetchUsers()) ;
        return new ResponseEntity<>(data, HttpStatus.OK) ;
    }


    @GetMapping(value = "/user/{name}")
    public ResponseEntity<Object> getUser(@PathVariable("name") String name) {
        Map<String, List<SecuredUser>> data = new HashMap<>()  ;
        data.put("users", this.userService.fetchUsers().stream().filter(u -> u.getName().equals(name)).collect(Collectors.toList())) ;
        if (data.get("users").size() == 0) {
            return new ResponseEntity<>("no users found...", HttpStatus.NOT_FOUND) ;
        }
        return new ResponseEntity<>(data, HttpStatus.OK) ;
    }


    @GetMapping(value = "/login") 
    public ResponseEntity<Object> loginUser(
        @RequestParam("email") String email, 
        @RequestParam("password") String password
    ){
        Map<String, SecuredUser> data = new HashMap<>() ;

        if (email == null || email.equals("") 
            || password == null || password.equals("")) {
                return new ResponseEntity<Object>("Bad request parameters...", HttpStatus.BAD_REQUEST) ;
        }

        try {
            
            data.put("user", this.userService.fetchUsers()
            .stream()
            .filter(u -> u.getEmail().equals(email) 
                    &&   u.getPassword().equals(password))
            .findAny()
            .get()) ;

        } catch (NoSuchElementException e) {
            
            e.printStackTrace() ;
            return new ResponseEntity<Object>("User not found...", HttpStatus.NOT_FOUND) ;
        
        }

        return new ResponseEntity<>(data, HttpStatus.OK) ;
    }

}
