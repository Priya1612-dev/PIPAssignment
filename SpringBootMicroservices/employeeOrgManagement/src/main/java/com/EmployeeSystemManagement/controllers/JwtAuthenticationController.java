package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.dto.LoginDetails;
import com.EmployeeSystemManagement.dto.TokenRefreshResponse;
import com.EmployeeSystemManagement.dto.UserLogin;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.jwtUtils.JwtUserDetailsService;
import com.EmployeeSystemManagement.service.JwtAuthenticationService;
import com.EmployeeSystemManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.logging.Logger;


@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLogin userLogin) throws Exception {
		LoginDetails details=jwtAuthenticationService.createAuthenticationToken(userLogin);
		return ResponseEntity.ok(details);
	}

}


