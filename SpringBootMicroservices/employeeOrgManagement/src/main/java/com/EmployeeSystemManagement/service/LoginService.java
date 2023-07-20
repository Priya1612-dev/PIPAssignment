package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dto.LoginDetails;
import com.EmployeeSystemManagement.dto.UserLogin;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.jwtUtils.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    UserService userService;
    public LoginDetails createAuthenticationToken(UserLogin userLogin) throws Exception {

        authenticate(userLogin.getUsername(), userLogin.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userLogin.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return new LoginDetails(token, userDetails.getUsername());
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }






}
