package com.auth.micro.security;

import static com.auth.micro.security.SecurityConstants.LOGIN_PATH;
import static com.auth.micro.security.SecurityConstants.SIGN_UP_URL;
import static com.auth.micro.security.SecurityConstants.USERS_RESOURCE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.auth.micro.services.UserLoginService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    private UserLoginService userLoginService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    public WebSecurity(UserLoginService userLoginService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.userLoginService      = userLoginService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {  
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL+USERS_RESOURCE).permitAll();
        http.authorizeRequests().anyRequest().authenticated().and().addFilter(getAuthenticationFilter()).addFilter(new AuthorizationFilter(authenticationManager()));
        http.sessionManagement().sessionCreationPolicy(STATELESS);
    }
    
    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter  authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl(LOGIN_PATH);
        return authenticationFilter;
        
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService).passwordEncoder(bCryptPasswordEncoder);
    }

}
