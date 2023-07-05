package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dao.UserRepository;
import com.EmployeeSystemManagement.dto.LoginDetails;
import com.EmployeeSystemManagement.dto.UserLogin;
import com.EmployeeSystemManagement.jwtUtils.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;


    public LoginDetails userLogin(UserLogin loginDetails) {

        this.doAuthenticate(loginDetails.getUsername(), loginDetails.getPassword());

        UserDetails userDetails = userRepo.findByUsername(loginDetails.getUsername());
        String token =generateToken(userDetails);

        LoginDetails response = LoginDetails.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return  response;
    }
    //@Cacheable(value = "")
    public String generateToken(UserDetails userDetails){
      return  this.helper.generateToken(userDetails);
    }

    private void doAuthenticate(String UserName, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(UserName, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }





}
