package com.EmployeeSystemManagement.controllers;

import com.EmployeeSystemManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<HttpStatus> readFileFromSftp(@RequestParam String fileName
                                                  ){
        employeeService.loadEmployee(fileName);
      return new ResponseEntity<>(HttpStatus.OK);
    }



}
