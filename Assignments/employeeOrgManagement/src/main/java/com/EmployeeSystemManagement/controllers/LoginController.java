package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.dto.LoginDetails;
import com.EmployeeSystemManagement.dto.UserLogin;
import com.EmployeeSystemManagement.jwtUtils.JwtHelper;
import com.EmployeeSystemManagement.service.LoginService;
import com.EmployeeSystemManagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

        @Autowired
        private UserService userService;
        @Autowired
        private LoginService loginService;
        @Autowired
        private AuthenticationManager manager;


        @Autowired
        private JwtHelper helper;

        private Logger logger = LoggerFactory.getLogger(LoginController.class);


        @GetMapping("/login")
        public ResponseEntity<LoginDetails> login(@RequestBody UserLogin loginDetails) throws Exception {

            LoginDetails response= loginService.createAuthenticationToken(loginDetails);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

}
