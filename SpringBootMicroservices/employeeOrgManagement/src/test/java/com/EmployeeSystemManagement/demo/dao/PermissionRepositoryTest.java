package com.EmployeeSystemManagement.demo.dao;

import com.EmployeeSystemManagement.dao.PermissionRepository;
import com.EmployeeSystemManagement.entities.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PermissionRepositoryTest {
        @Autowired
        private PermissionRepository permissionRepo;

        @Test
        public void findAllRoles_test() {
            List<Permission> roleList = permissionRepo.findAll();
            assertThat(roleList.size()).isGreaterThan(0);
        }

        @Test
        public void findUserById() {
            List<Permission> userList = (List<Permission>) permissionRepo.findAll();
            Permission find = permissionRepo.findById(userList.get(0).getPermissionId()).get();
            assertNotNull(find);
        }


        @Test
        public void updateUser_test(){
            List<Permission> userList = (List<Permission>) permissionRepo.findAll();
            Permission oldRole = permissionRepo.findById(userList.get(0).getPermissionId()).get();
            oldRole.setName("manager");
            Permission newRole = permissionRepo.save(oldRole);
            assertEquals(oldRole.getName(), newRole.getName());

        }
        @Test
        public  void deleteUser_test() {
            List<Permission> empList = (List<Permission>) permissionRepo.findAll();
            Permission user = permissionRepo.findById(empList.get(0).getPermissionId()).get();
            assertNotNull(user.getPermissionId());
            permissionRepo.deleteById(user.getPermissionId());

        }

}
