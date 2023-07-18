package com.employeePermissionManagement.controller;

import com.employeePermissionManagement.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
public class ExcelController {
    @Value("${EXCEL_FILEPATH}")
    private String filePath;
    @Autowired
    private ExcelService excelService;
    @GetMapping("/readExcel")
    public ResponseEntity<HttpStatus> readXlsFile() throws IOException {
       excelService.save(filePath);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
