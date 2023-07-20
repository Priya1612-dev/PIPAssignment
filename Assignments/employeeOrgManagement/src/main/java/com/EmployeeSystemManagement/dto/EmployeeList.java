package com.EmployeeSystemManagement.dto;


import com.EmployeeSystemManagement.DataMapper.Employee;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeList {
    List<Employee> employeeList;
}
