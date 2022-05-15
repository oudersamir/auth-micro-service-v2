package com.auth.micro.controllers;

import static com.auth.micro.security.SecurityConstants.SIGN_UP_URL;
import static com.auth.micro.security.SecurityConstants.USERS_RESOURCE;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping(path=USERS_RESOURCE,consumes= {APPLICATION_XML_VALUE,APPLICATION_JSON_VALUE}
                                    ,produces= {APPLICATION_XML_VALUE,APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserDto userDto = new UserDto();

        copyProperties(userRequest, userDto);

        UserDto createdUserDto = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();

        copyProperties(createdUserDto, userResponse);

        return new ResponseEntity<UserResponse>(userResponse, CREATED);
    }
    
    @GetMapping(path=USERS_RESOURCE+"/{userName}",produces= {APPLICATION_XML_VALUE,APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable String userName)  {

        UserDto userDto = userService.getUserByUserName(userName);

        UserResponse userResponse = new UserResponse();

        copyProperties(userDto, userResponse);

        return new ResponseEntity<UserResponse>(userResponse, FOUND);
    }
    
    @DeleteMapping(path=USERS_RESOURCE+"/{userId}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable String userId) {

        userService.deleteUserByUserId(userId);

        return new ResponseEntity<UserResponse>(NO_CONTENT);
    }
    
    @PutMapping(path=USERS_RESOURCE+"/{userId}",consumes= {APPLICATION_XML_VALUE,APPLICATION_JSON_VALUE}
                                               ,produces= {APPLICATION_XML_VALUE,APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
       
        UserDto userDto = new UserDto();
        
        copyProperties(userRequest, userDto);
        
        userDto.setUserId(userId);
        
        userService.updateUser(userDto);

        return new ResponseEntity<UserResponse>(ACCEPTED);
    }

}
