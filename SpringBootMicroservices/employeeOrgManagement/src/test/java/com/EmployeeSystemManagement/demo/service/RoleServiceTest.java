package com.EmployeeSystemManagement.demo.service;

import com.EmployeeSystemManagement.dao.RoleRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

        @Mock
        private RoleRepository roleRepo;

        @InjectMocks
        private RoleService roleService;

        @Test
        void test_findAll() {
            Set<User> userList = new HashSet<>();
            Set<Permission> permissions=new HashSet<>();
            List<Role> rolesList=new ArrayList<>();
            rolesList.add(new Role(1L,"admin","has all rights",UserStatus.ADMIN,"ebjhedbc@",userList,permissions));
            rolesList.add(new Role(2L,"user","has some rights",UserStatus.REGULAR,"cdhyyesu",userList,permissions));

            when(roleRepo.findAll()).thenReturn(rolesList);
            assertThat(rolesList.size()).isGreaterThan(0);
        }

        @Test
        void test_findOneRole() {
            Set<User> userList = new HashSet<>();
            Set<Permission> permissions=new HashSet<>();
            Optional<Role> role1 = Optional.ofNullable(new Role(2L,"user","has some rights",UserStatus.REGULAR,"cdhyyesu",userList,permissions));
            when(roleRepo.findById(Mockito.anyLong())).thenReturn(role1);
            assertNotNull(role1);
        }

        @Test
        void test_createTask() {
            Long id = 1L;
            Set<User> userList = new HashSet<>();
            Set<Permission> permissions=new HashSet<>();
            Role role1 =new Role(2L,"user","has some rights",UserStatus.REGULAR,"cdhyyesu",userList,permissions);

            when(roleRepo.save(Mockito.any(Role.class))).thenReturn(role1);
            assertNotNull(role1);
        }

        @Test
        void test_updateOneRole() {
            Set<User> userList = new HashSet<>();
            Set<Permission> permissions=new HashSet<>();
            Optional<Role> role1 = Optional.ofNullable(new Role(2L,"user","has some rights",UserStatus.REGULAR,"cdhyyesu",userList,permissions));
            when(roleRepo.findById(Mockito.anyLong())).thenReturn(role1);
            // set new value
            role1.get().setName("xyz");
            when(roleRepo.save(Mockito.any(Role.class))).thenReturn(role1.get());
            assertNotNull(role1);
        }

        @Test
        void test_deleteOne() {
            doNothing().when(roleRepo).deleteById(Mockito.anyLong());
        }

    }
