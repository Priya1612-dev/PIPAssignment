package com.employeeCrudDetails.employeePermissionManagement.service;

import com.employeeCrudDetails.employeePermissionManagement.Helper.XlsHelper;
import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import com.employeeCrudDetails.employeePermissionManagement.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ExcelService {
@Autowired
    private XlsHelper helper;
@Autowired
private EmployeeRepository employeeRepo;
    @Value("${EXCEL_FILEPATH}")
    private String filePath;
    public List<EmployeeDetails> save() throws FileNotFoundException {
        File file=new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
        List<EmployeeDetails> employeeDetailsList=helper.readXlsFile(inputStream);
        employeeRepo.saveAll(employeeDetailsList);
        return employeeDetailsList;

    }
}
