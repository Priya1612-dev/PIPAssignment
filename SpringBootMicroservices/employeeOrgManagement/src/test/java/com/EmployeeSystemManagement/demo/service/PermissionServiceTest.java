package com.EmployeeSystemManagement.demo.service;

import com.EmployeeSystemManagement.dao.PermissionRepository;
import com.EmployeeSystemManagement.dao.RoleRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.service.PermissionService;
import com.EmployeeSystemManagement.service.RoleService;
import jakarta.persistence.*;
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
public class PermissionServiceTest {

        @Mock
        private PermissionRepository permissionRepo;

        @InjectMocks
        private PermissionService permissionService;

        @Test
        void test_findAll() {
            Set<Role> roleList = new HashSet<>();
            List<Permission> permissionList=new ArrayList<>();
            permissionList.add(new Permission(1L,"readAndWrite","have all rights",UserStatus.ADMIN,roleList));
            permissionList.add(new Permission(2L,"read","have only read rights",UserStatus.REGULAR,roleList));

            when(permissionRepo.findAll()).thenReturn(permissionList);
            assertThat(permissionList.size()).isGreaterThan(0);
        }

        @Test
        void test_findOne() {
            Set<Role> roleList = new HashSet<>();
            Optional<Permission> permission1 = Optional.ofNullable(new Permission(1L,"readAndWrite","have all rights",UserStatus.ADMIN,roleList));
            when(permissionRepo.findById(Mockito.anyLong())).thenReturn(permission1);
            assertNotNull(permission1);
        }

        @Test
        void test_createTask() {
            Long id = 2L;
            Set<Role> roleList = new HashSet<>();
            Permission permission1 =new Permission(1L,"readAndWrite","have all rights",UserStatus.ADMIN,roleList);
            when(permissionRepo.save(Mockito.any(Permission.class))).thenReturn(permission1);
            assertNotNull(permission1);
        }

        @Test
        void test_updateOne() {
            Set<Role> roleList = new HashSet<>();
            Optional<Permission> permission1 = Optional.ofNullable(new Permission(1L,"readAndWrite","have all rights",UserStatus.ADMIN,roleList));

            when(permissionRepo.findById(Mockito.anyLong())).thenReturn(permission1);
            // set new value
            permission1.get().setName("xyz");
            when(permissionRepo.save(Mockito.any(Permission.class))).thenReturn(permission1.get());
            assertNotNull(permission1);
        }

        @Test
        void test_deleteOne() {
            doNothing().when(permissionRepo).deleteById(Mockito.anyLong());
        }


    }
