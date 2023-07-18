package com.EmployeeOrgManagement.controllers;


import com.EmployeeOrgManagement.dao.EmployeeRepository;
import com.EmployeeOrgManagement.entities.Employees;
import com.EmployeeOrgManagement.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


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

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/demo")
    public  String getToken() {
//        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//
//        String value = null;
//        Set<String> redisKeys = redisTemplate.keys("*");
//        System.out.println(redisTemplate);
//        System.out.println(redisTemplate.getConnectionFactory());
//        System.out.println(redisTemplate.keys(""));
//        //System.out.println(redisConnection.isClosed());
//        System.out.println(redisTemplate.isExposeConnection());
//        List<String> keysList = new ArrayList<>();
//        System.out.println(redisKeys);
//        Iterator<String> it = redisKeys.iterator();
//
//        while (it.hasNext()) {
//            String data = it.next();
//            if (data.equals("loginDetails.token::priya16")) {
//                value = redisTemplate.type("loginDetails.token::priya16").toString();
//                System.out.println(value);
//            }
//            //  keysList.add(data);
//        }
//
//        //redisConnection.close();
//    return  value;
        String value=null;
        JedisPoolConfig jedisConfig=new JedisPoolConfig();
        jedisConfig.setMaxTotal(10);
        JedisPool jedisPool=new JedisPool(jedisConfig,"localhost",6379);
        try(Jedis jedis=jedisPool.getResource()){
            value=jedis.get("loginDetails.token::priya16");
            System.out.println(value);
        }
        jedisPool.close();

        return value;

    }


}
