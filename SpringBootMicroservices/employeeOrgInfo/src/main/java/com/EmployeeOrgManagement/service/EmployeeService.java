package com.EmployeeOrgManagement.service;

import com.EmployeeOrgManagement.dao.EmployeeRepository;
import com.EmployeeOrgManagement.dto.EmployeeList;
import com.EmployeeOrgManagement.entities.Employees;
import org.hibernate.query.sqm.mutation.internal.temptable.LocalTemporaryTableInsertStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

        @Autowired
        private EmployeeRepository employeeRepo;



    public void validateAndSaveEmployee(List<Employees> employees/*,String authToken*/){
  // tokenUtil.validateToken(authToken,Employee);

           employeeRepo.saveAll(employees);

    }

}
