package com.EmployeeSystemManagement.demo.dao;


import com.EmployeeSystemManagement.dao.RoleRepository;
import com.EmployeeSystemManagement.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepo;

    @Test
    public void findAllRoles_test() {
        List<Role> roleList = (List<Role>) roleRepo.findAll();
        assertThat(roleList.size()).isGreaterThan(0);
    }

    @Test
    public void findUserById() {
        List<Role> userList = (List<Role>) roleRepo.findAll();
        Role find = roleRepo.findById(userList.get(0).getRoleId()).get();
        assertNotNull(find);
    }


    @Test
    public void updateUser_test(){
        List<Role> userList = (List<Role>) roleRepo.findAll();
        Role oldRole = roleRepo.findById(userList.get(0).getRoleId()).get();
        oldRole.setName("manager");
        Role newRole = roleRepo.save(oldRole);
        assertEquals(oldRole.getName(), newRole.getName());

    }
    @Test
    public  void deleteUser_test() {
        List<Role> empList = (List<Role>) roleRepo.findAll();
        Role user = roleRepo.findById(empList.get(0).getRoleId()).get();
        assertNotNull(user.getRoleId());
        roleRepo.deleteById(user.getRoleId());

    }


}
