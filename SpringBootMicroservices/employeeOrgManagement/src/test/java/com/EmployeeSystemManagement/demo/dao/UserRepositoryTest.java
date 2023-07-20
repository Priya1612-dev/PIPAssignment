package com.EmployeeSystemManagement.demo.dao;

import com.EmployeeSystemManagement.dao.UserRepository;
import com.EmployeeSystemManagement.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userrepo;

    @Test
    public void findAllUser_test() {
        List<User> userList = (List<User>) userrepo.findAll();
        assertThat(userList.size()).isGreaterThan(0);
    }

    @Test
    public void findUserById() {
        List<User> userList = (List<User>) userrepo.findAll();
        User find = userrepo.findById(userList.get(0).getUserId()).get();
        assertNotNull(find);
    }


    @Test
    public void updateUser_test(){
        List<User> userList = (List<User>) userrepo.findAll();
        User oldEmp = userrepo.findById(userList.get(0).getUserId()).get();
        oldEmp.setEmail("ppaaaa@gmail.com");
        User newUser = userrepo.save(oldEmp);
        assertEquals(oldEmp.getEmail(), newUser.getEmail());

    }
    @Test
    public  void deleteUser_test() {
        List<User> empList = (List<User>) userrepo.findAll();
        User user = userrepo.findById(empList.get(0).getUserId()).get();
        assertNotNull(user.getUserId());
        userrepo.deleteById(user.getUserId());

    }
}