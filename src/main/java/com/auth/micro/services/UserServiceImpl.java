package com.auth.micro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.micro.entities.UserEntity;
import com.auth.micro.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity userToSave) {

        UserEntity userFromDb = userRepository.findByEmail(userToSave.getEmail());
        UserEntity newUser    = null;

        if (userFromDb == null) {
            newUser = userRepository.save(userToSave);
        } else {
            throw new RuntimeException(String.format("User  {} already exist !! ", userToSave.getEmail()));
        }

        return newUser;
    }

}
