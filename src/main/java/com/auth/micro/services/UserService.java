package com.auth.micro.services;

import java.util.List;

import com.auth.micro.dto.UserDto;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto getUser(String email);
    public UserDto getUserByUserName(String userName);
    public boolean deleteUserByUserId(String userId);
    public boolean updateUser(UserDto userDto);
    public List<UserDto> getUsers(int page, int limit);

}
