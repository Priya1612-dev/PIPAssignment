package com.EmployeeOrgManagement.controllers;

import com.EmployeeOrgManagement.dto.EmployeeList;
import com.EmployeeOrgManagement.dao.EmployeeRepository;
import com.EmployeeOrgManagement.entities.Employees;
import com.EmployeeOrgManagement.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepo;
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/saveEmployee")
    public void validateAndSaveEmployee(
                                                /*  @RequestHeader("Authorization") String authToken,*/
                                                  @RequestBody List<Employees> employeeList){

    employeeService.validateAndSaveEmployee(employeeList);

    }


}
