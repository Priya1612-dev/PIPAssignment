package com.EmployeeOrgManagement.dao;

import com.EmployeeOrgManagement.entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employees,Integer> {

}
