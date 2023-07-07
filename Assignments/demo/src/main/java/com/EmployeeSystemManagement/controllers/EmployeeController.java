//package com.EmployeeSystemManagement.controllers;
//
//import com.EmployeeSystemManagement.DataMapper.Employee;
//import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmployeeController {
//    @Autowired
//    private JwtTokenUtil tokenUtil;
//    @PostMapping("/employee")
//    public ResponseEntity<String> validateAndSaveEmployee(@RequestBody Employee employee,
//                                                  @RequestHeader("Authorization") String authToken){
//    //validate token
//        tokenUtil.validateToken(authToken,Employee);
//
//
//        return new ResponseEntity<>("Token Validated", HttpStatus.OK);
//    }
//}
