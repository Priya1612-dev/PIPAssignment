package com.EmployeeOrgManagement.service;

import com.EmployeeOrgManagement.dao.EmployeeRepository;
import com.EmployeeOrgManagement.dto.EmployeeList;
import com.EmployeeOrgManagement.entities.Employees;
import org.hibernate.query.sqm.mutation.internal.temptable.LocalTemporaryTableInsertStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

        @Autowired
        private EmployeeRepository employeeRepo;
//    @Autowired
//     private RedisTemplate<String, String> redisTemplate;


    public void validateAndSaveEmployee(List<Employees> employees/*,String authToken*/){
  // tokenUtil.validateToken(authToken,Employee);
       // String value=getTokenFromCache("loginDetails.token::priya16");
        //System.out.println(value);
           employeeRepo.saveAll(employees);


    }

//    public String getTokenFromCache(String key){
//     RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
//        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
//        String value=null;
//        Set<String> redisKeys = redisTemplate.keys("");
//        List<String> keysList = new ArrayList<>();
//        Iterator<String> it = redisKeys.iterator();
//        while (it.hasNext()) {
//            String data = it.next();
//            if(data.equals(key)){
//              value= redisTemplate.type(key).toString();
//            }
//          //  keysList.add(data);
//        }
//        redisConnection.close();
//        return value;
//    }

}
