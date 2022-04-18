package com.auth.micro.controllers;

import static com.auth.micro.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.micro.dto.UserDto;
import com.auth.micro.requests.UserRequest;
import com.auth.micro.responses.UserResponse;
import com.auth.micro.services.UserService;

@RestController
@RequestMapping(SIGN_UP_URL+"/*")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userRequest, userDto);

        UserDto createdUserDto = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();

        BeanUtils.copyProperties(createdUserDto, userResponse);

        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

}
