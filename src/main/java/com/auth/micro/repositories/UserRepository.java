package com.auth.micro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.micro.entities.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

    public UserEntity findByUserName(String userName);

}
