package com.EmployeeOrgManagement.dto;

import com.EmployeeOrgManagement.entities.Employees;
import lombok.Data;

import java.util.List;
@Data
public class EmployeeList {
    List<Employees> employeeList;
}
