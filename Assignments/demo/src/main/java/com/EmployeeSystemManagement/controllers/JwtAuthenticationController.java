package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.dto.JwtResponse;
import com.EmployeeSystemManagement.dto.JwtRequest;
import com.EmployeeSystemManagement.dto.TokenRefreshResponse;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.jwtUtils.JwtUserDetailsService;
import com.EmployeeSystemManagement.service.JwtAuthenticationService;
import com.EmployeeSystemManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationService jwtAuthenticationService;

	@Autowired
	UserService userService;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest userLogin) throws Exception {
		JwtResponse response= jwtAuthenticationService.createAuthenticationToken(userLogin);
		return ResponseEntity.ok(response);
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}

@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
public ResponseEntity<TokenRefreshResponse> refreshtoken(HttpServletRequest request) throws Exception {
	String token= jwtAuthenticationService.refreshtoken(request);
	return ResponseEntity.ok(new TokenRefreshResponse(token));
}


}