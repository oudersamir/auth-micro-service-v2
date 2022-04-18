package com.auth.micro.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.micro.dao.UserDto;
import com.auth.micro.requests.UserRequest;
import com.auth.micro.responses.UserResponse;
import com.auth.micro.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userRequest, userDto);

        UserDto createdUserDto = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(createdUserDto, userResponse);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }


}
