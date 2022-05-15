package com.auth.micro.security;

import static com.auth.micro.SpringApplicationContext.getBean;
import static com.auth.micro.security.SecurityConstants.EXPIRATION_TIME;
import static com.auth.micro.security.SecurityConstants.HEADER_AUTHORIZATION;
import static com.auth.micro.security.SecurityConstants.TOKEN_PREFIX;
import static com.auth.micro.security.SecurityConstants.TOKEN_SECRET;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.System.currentTimeMillis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth.micro.SpringApplicationContext;
import com.auth.micro.dto.UserDto;
import com.auth.micro.requests.UserLoginRequest;
import com.auth.micro.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;
    

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserLoginRequest userLogin = null;
        try {
            userLogin = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword(), new ArrayList<>()));
    }
    
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName =((User)authResult.getPrincipal()).getUsername();
        
        String token = Jwts.builder()
                           .setSubject(userName)
                           .setExpiration(new Date(currentTimeMillis()+EXPIRATION_TIME))
                           .signWith(HS512, TOKEN_SECRET)
                           .compact();
        //
        response.addHeader(HEADER_AUTHORIZATION,TOKEN_PREFIX+token);
        //
        UserDto userDto = ((UserService)getBean("userServiceImpl")).getUser(userName);
       
        response.addHeader("user_id",userDto.getUserId());

        
    }

}
