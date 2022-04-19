package com.auth.micro.services;

import static org.springframework.beans.BeanUtils.copyProperties;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.micro.dto.UserDto;
import com.auth.micro.entities.UserEntity;
import com.auth.micro.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    

    @Override
    public UserDto createUser(UserDto userToSave) {

        UserEntity userFromDb     = userRepository.findByEmail(userToSave.getEmail());
        UserDto    createdUserDto = null;

        if (userFromDb == null) {

            UserEntity newUser = new UserEntity();

            BeanUtils.copyProperties(userToSave, newUser);
            
            newUser.setUserId("user Id");
            newUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));


            UserEntity createdUser = userRepository.save(newUser);

            createdUserDto = new UserDto();

            BeanUtils.copyProperties(createdUser, createdUserDto);

        } else {
            throw new RuntimeException(String.format("User  {} already exist !! ", userToSave.getEmail()));
        }

        return createdUserDto;
    }



    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        //
        if (null == userEntity) {
            throw new UsernameNotFoundException(email);
        }
        //
        UserDto userDto = new UserDto();
        copyProperties(userEntity, userDto);

        return userDto;
    }

}
