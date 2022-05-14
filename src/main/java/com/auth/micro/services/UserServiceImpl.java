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
import com.auth.micro.shared.Utils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Utils utils;

    @Override
    public UserDto createUser(UserDto userToSave) {

        UserEntity userFromDb     = userRepository.findByEmail(userToSave.getEmail());
        UserDto    createdUserDto = null;

        if (userFromDb == null) {

            UserEntity newUser = new UserEntity();

            BeanUtils.copyProperties(userToSave, newUser);
            
            newUser.setUserId(utils.generateStringId(14));
            
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
    
    @Override
    public UserDto getUserByUserName(String userName) {
        UserEntity userEntity = userRepository.findByUserName(userName);
        //
        if (null == userEntity) {
            throw new UsernameNotFoundException(userName);
        }
        //
        UserDto userDto = new UserDto();
        copyProperties(userEntity, userDto);

        return userDto;
    }



    @Override
    public boolean deleteUserByUserId(String userId) {

        UserEntity userEntity = userRepository.findByUserId(userId);
        //
        if (null == userEntity) {
            throw new UsernameNotFoundException(userId);
        }
        //
        userRepository.delete(userEntity);

        return true;
    }



    @Override
    public boolean updateUser(UserDto userDto) {

          UserEntity userEntity =  userRepository.findByUserId(userDto.getUserId());
          //
          if (null == userEntity) {
              throw new UsernameNotFoundException(userDto.getUserId());
          }
          //
          userEntity.setNom(userDto.getNom());
          userEntity.setPrenom(userDto.getPrenom());
          userEntity.setUserName(userDto.getUserName());
          userEntity.setEmail(userDto.getEmail());
          userRepository.save(userEntity);
        
        return true;
    }

}
