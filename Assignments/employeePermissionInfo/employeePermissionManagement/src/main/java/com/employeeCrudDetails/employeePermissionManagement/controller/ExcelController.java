package com.employeeCrudDetails.employeePermissionManagement.controller;

import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import com.employeeCrudDetails.employeePermissionManagement.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;
    @GetMapping("/readExcel")
    public ResponseEntity<List<EmployeeDetails>> readXlsFile() throws FileNotFoundException {
        List<EmployeeDetails> details= excelService.save();
        return new ResponseEntity<>(details,HttpStatus.OK);
    }
}
