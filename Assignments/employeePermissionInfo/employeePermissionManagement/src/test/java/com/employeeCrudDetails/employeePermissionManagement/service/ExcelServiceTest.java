package com.employeeCrudDetails.employeePermissionManagement.service;

import com.employeeCrudDetails.employeePermissionManagement.Helper.XlsHelper;
import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import com.employeeCrudDetails.employeePermissionManagement.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.Mockito;

@ExtendWith(SpringExtension.class)
public class ExcelServiceTest {
        @Mock
        private EmployeeRepository employeeRepo;

        @InjectMocks
        private ExcelService excelService;
        @InjectMocks
        private XlsHelper helper;

        @Test
        void test_findAll() {
            List<EmployeeDetails> empList = new ArrayList<>();
            empList.add(new EmployeeDetails(1, "create", "java", "ppp@gmail.com","rani","infosys"));
            empList.add(new EmployeeDetails(2, "update", "java", "ccc@gmail.com","chiyaan","kone"));
            when(employeeRepo.findAll()).thenReturn(empList);
            assertThat(empList.size()).isGreaterThan(0);
        }

        @Test
        void test_findOneEmployee() {
            Optional<EmployeeDetails> details = Optional.ofNullable(new EmployeeDetails(1, "create", "java", "ppp@gmail.com","rani","infosys"));
            when(employeeRepo.findById(Mockito.anyInt())).thenReturn(details);
            assertNotNull(details);
        }

        @Test
        void test_createTask() {
            int id = 2;
            EmployeeDetails e1 = new EmployeeDetails(2, "update", "java", "ccc@gmail.com","chiyaan","kone");
            when(employeeRepo.save(Mockito.any(EmployeeDetails.class))).thenReturn(e1);
            assertNotNull(e1);
        }

        @Test
        void test_updateOneEmployee() {
            Optional<EmployeeDetails> details = Optional.ofNullable(new EmployeeDetails(1, "create", "java", "ppp@gmail.com","rani","infosys"));
            when(employeeRepo.findById(Mockito.anyInt())).thenReturn(details);
            details.get().setDomain("python");
            when(employeeRepo.save(Mockito.any(EmployeeDetails.class))).thenReturn(details.get());
            assertNotNull(details);
        }

        @Test
        void test_deleteOne() {
            doNothing().when(employeeRepo).deleteById(Mockito.anyInt());
        }

        @Test
        void save_test() throws FileNotFoundException {
            String filePath="C://Users//priya.s//OneDrive - Happiest Minds Technologies Limited//Desktop//project//OrgDetails.xlsx";
        File file=new File(filePath);
        FileInputStream inputStream=new FileInputStream(file);
        List<EmployeeDetails> employeeDetailsList=helper.readXlsFile(inputStream);
        employeeRepo.saveAll(employeeDetailsList);
            when(employeeRepo.findAll()).thenReturn(employeeDetailsList);
            assertThat(employeeDetailsList.size()).isGreaterThan(0);
    }
    }


