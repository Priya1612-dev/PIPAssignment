package com.employeeCrudDetails.employeePermissionManagement.repository;

import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDetails,Integer> {

}
