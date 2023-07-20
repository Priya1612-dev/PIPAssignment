package com.EmployeeOrgManagement.controllers;


import com.EmployeeOrgManagement.dao.EmployeeRepository;
import com.EmployeeOrgManagement.entities.Employees;
import com.EmployeeOrgManagement.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
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
            @RequestBody List<Employees> employeeList) {

        employeeService.validateAndSaveEmployee(employeeList);

    }
    @GetMapping("/demo")
    public  String getToken() {

        String value=null;
        JedisPoolConfig jedisConfig=new JedisPoolConfig();
        jedisConfig.setMaxTotal(10);
        JedisPool jedisPool=new JedisPool(jedisConfig,"localhost",6379);
        try(Jedis jedis=jedisPool.getResource()){
            value=jedis.get("loginDetails.token::priya16");
        }
        jedisPool.close();

        return value;

    }


}
