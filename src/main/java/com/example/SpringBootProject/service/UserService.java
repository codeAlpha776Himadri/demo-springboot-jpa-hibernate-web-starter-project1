package com.example.SpringBootProject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringBootProject.model.SecuredUser;

@Service
public class UserService {
    
    private List<SecuredUser> users ; 

    public UserService() {
        super() ; 
        this.users = new ArrayList<>() ;
        users.add(new SecuredUser("name1" , "email1" , "password1")) ;
        users.add(new SecuredUser("name2" , "email2" , "password2")) ;
        users.add(new SecuredUser("name3" , "email3" , "password3")) ;
    }


    public List<SecuredUser> fetchUsers() {
        return this.users ;
    }

    public boolean addUser(SecuredUser user) {
        this.users.add(user);
        return true ; 
    }

}
