package com.employeeCrudDetails.employeePermissionManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {
        @Autowired
        private EmployeeRepository employeeRepo;

        @Test
        void test_createTask() {
            EmployeeDetails details = new EmployeeDetails(1, "create", "java", "ppp@gmail.com","rani","infosys");
            details = employeeRepo.save(details);
            assertNotNull(details);
            assertNotNull(details.getEmpId());
        }

        @Test
        void test_updateOneEmployee() {
            List<EmployeeDetails> empList = (List<EmployeeDetails>) employeeRepo.findAll();
            EmployeeDetails oldEmp = employeeRepo.findById(empList.get(0).getEmpId()).get();
            oldEmp.setDomain("python");
            EmployeeDetails newEmp = employeeRepo.save(oldEmp);
            assertEquals(oldEmp.getDomain(), newEmp.getDomain());

        }

        @Test
        void test_findAll() {
            List<EmployeeDetails> empList = (List<EmployeeDetails>) employeeRepo.findAll();
            assertThat(empList.size()).isGreaterThan(0);
        }

        @Test
        void test_findOneEmployee() {

            List<EmployeeDetails> empList = (List<EmployeeDetails>) employeeRepo.findAll();
            EmployeeDetails find = employeeRepo.findById(empList.get(0).getEmpId()).get();
            assertNotNull(find);

        }

        @Test
        void test_deleteOne() {
            List<EmployeeDetails> empList = (List<EmployeeDetails>) employeeRepo.findAll();
            EmployeeDetails emp = employeeRepo.findById(empList.get(0).getEmpId()).get();
            assertNotNull(emp.getEmpId());
            employeeRepo.deleteById(emp.getEmpId());

        }

    }
