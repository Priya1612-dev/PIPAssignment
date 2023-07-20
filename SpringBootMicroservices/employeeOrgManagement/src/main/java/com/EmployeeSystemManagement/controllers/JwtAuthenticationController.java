package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.dto.LoginDetails;
import com.EmployeeSystemManagement.dto.UserLogin;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;



	@PostMapping("/login")
	public ResponseEntity<LoginDetails> createAuthenticationToken(@RequestBody UserLogin userLogin) throws Exception {
		LoginDetails details=jwtAuthenticationService.createAuthenticationToken(userLogin);
		return ResponseEntity.ok(details);
	}



}


