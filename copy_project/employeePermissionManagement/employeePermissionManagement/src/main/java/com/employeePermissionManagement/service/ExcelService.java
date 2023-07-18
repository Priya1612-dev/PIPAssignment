package com.employeePermissionManagement.service;

import com.employeePermissionManagement.Repository.EmployeeRepository;
import com.employeePermissionManagement.dto.EmployeeDetails;
import com.employeePermissionManagement.helper.EmployeeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class ExcelService {


    public void save(String filePath) throws FileNotFoundException {
        File file=new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
            List<EmployeeDetails> employeeDetailsList=EmployeeHelper.convertExcelToList(inputStream);
           // employeeRepo.saveAll(employeeDetailsList);

    }
}
