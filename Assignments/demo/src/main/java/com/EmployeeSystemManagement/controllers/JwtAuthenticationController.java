package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.dto.JwtResponse;
import com.EmployeeSystemManagement.dto.JwtRequest;
import com.EmployeeSystemManagement.dto.TokenRefreshRequest;
import com.EmployeeSystemManagement.dto.TokenRefreshResponse;
import com.EmployeeSystemManagement.exceptions.TokenRefreshException;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.jwtUtils.JwtUserDetailsService;
import com.EmployeeSystemManagement.service.LoginService;
import com.EmployeeSystemManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
	UserService userService;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest userLogin) throws Exception {
		LoginService.createAuthenticationToken(userLogin);
		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
	}
	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
//		return ResponseEntity.ok(userDetailsService.save(user));
//	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//		String requestRefreshToken = request.getRefreshToken();
//
//		return refreshTokenService.findByToken(requestRefreshToken)
//				.map(refreshTokenService::verifyExpiration)
//				.map(RefreshToken::getUser)
//				.map(user -> {
//					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//				})
//				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//						"Refresh token is not in database!"));
	}


}