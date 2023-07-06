package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dto.JwtRequest;
import com.EmployeeSystemManagement.dto.JwtResponse;
import com.EmployeeSystemManagement.dto.TokenRefreshRequest;
import com.EmployeeSystemManagement.dto.TokenRefreshResponse;
import com.EmployeeSystemManagement.exceptions.TokenRefreshException;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.EmployeeSystemManagement.jwtUtils.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
    public JwtResponse createAuthenticationToken(JwtRequest userLogin) throws Exception {

        authenticate(userLogin.getUsername(), userLogin.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userLogin.getUsername());

//		User user = userService.getUserByUserName(userLogin.getUsername());


        final String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtResponse(token, userDetails.getUsername());
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
    public ResponseEntity<?> refreshtoken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtTokenUtil.generateToken(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }





}
