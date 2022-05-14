package com.auth.micro.services;

import com.auth.micro.dto.UserDto;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto getUser(String email);
    public UserDto getUserByUserName(String userName);
    public boolean deleteUserByUserId(String userId);
    public boolean updateUser(UserDto userDto);


}
