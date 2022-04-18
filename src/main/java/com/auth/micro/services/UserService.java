package com.auth.micro.services;

import com.auth.micro.dao.UserDto;

public interface UserService {

    public UserDto createUser(UserDto userDto);

}
