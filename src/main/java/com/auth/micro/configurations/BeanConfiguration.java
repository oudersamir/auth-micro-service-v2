package com.auth.micro.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth.micro.SpringApplicationContext;
import com.auth.micro.services.UserService;
import com.auth.micro.services.UserServiceImpl;

@Configuration
public class BeanConfiguration {

    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext springApplicationContext() {
        return new SpringApplicationContext();
    }

}  
