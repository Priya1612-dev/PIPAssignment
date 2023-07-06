//package com.EmployeeSystemManagement.controllers;
//
//import com.EmployeeSystemManagement.DataMapper.Employee;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class EmployeeController {
//    @PostMapping("/employee")
//    public ResponseEntity<String> validateAndSaveEmployee(@RequestBody Employee employee,
//                                                  @RequestHeader("Authorization") String authToken)){
//    //validate token
//
//        return new ResponseEntity<>("Token Validated", HttpStatus.OK);
//    }
//}
