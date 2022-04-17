package com.auth.micro.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.micro.requests.UserRquest;

@RestController
@RequestMapping("/users")
public class UserController {
    
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRquest userReuest){
        
        return null;
    }


}
