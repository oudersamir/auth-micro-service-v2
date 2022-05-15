package com.auth.micro.services;

import static com.auth.micro.responses.ErrorMessageResponse.NO_RECORD_FOUND;
import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.micro.dto.UserDto;
import com.auth.micro.entities.UserEntity;
import com.auth.micro.exceptions.BusinessResourceException;
import com.auth.micro.repositories.UserRepository;
import com.auth.micro.responses.ErrorMessageResponse;
import com.auth.micro.responses.UserException;
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
            throw new BusinessResourceException(new Date(), "UserAlreadyExist", String.format("User %s already exist !! ", userToSave.getEmail()), NOT_ACCEPTABLE);
        }

        return createdUserDto;
    }



    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        //
        if (null == userEntity) {
            throw new BusinessResourceException(new Date(), "UserNotFound", format("User %s not found !! ", email), NOT_FOUND);
        }
        //
        UserDto userDto = new UserDto();
        copyProperties(userEntity, userDto);

        return userDto;
    }
     
    @Override
    public UserDto getUserByUserName(String userName) throws UserException {
        UserEntity userEntity = userRepository.findByUserName(userName);
        //
        if (null == userEntity) {
            throw new BusinessResourceException(new Date(), "UserNotFound", format("User %s not found !! ", userName), NOT_FOUND);
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
            throw new BusinessResourceException(new Date(), "UserNotFound", format("User %s not found !! ", userId), NOT_FOUND);
        }
        //
        userRepository.delete(userEntity);

        return true;
    }



    @Override
    public boolean updateUser(UserDto userDto) {

        UserEntity userEntity = userRepository.findByUserId(userDto.getUserId());
        //
        if (null == userEntity) {
            throw new BusinessResourceException(new Date(), "UserNotFound", format("User %s not found !! ", userDto.getUserId()), NOT_FOUND);

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
