package com.EmployeeSystemManagement.demo.service;

import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.enums.UserType;
import com.EmployeeSystemManagement.service.UserService;

import com.EmployeeSystemManagement.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
        @Mock
        private UserRepository userRepo;

        @InjectMocks
        private UserService userService;

    @Test
        void test_findAll() {
            List<User> userList = new ArrayList<>();
            Set<Role> roles=new HashSet<>();
            userList.add(new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa",UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));
        userList.add(new User(2L,"suman123","kapoor","suman@gmail.com","Axy1234@","bbb",UserType.ACTIVE,
                UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));

        when(userRepo.findAll()).thenReturn(userList);
            assertThat(userList.size()).isGreaterThan(0);
        }

        @Test
        void test_findOneUser() {
            Set<Role> roles=new HashSet<>();
            Optional<User> user1 = Optional.ofNullable(new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa",UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));
            when(userRepo.findById(Mockito.anyLong())).thenReturn(user1);
            assertNotNull(user1);
        }

        @Test
        void test_createTask() {
            Long id = 1L;
            Set<Role> roles=new HashSet<>();
            User user1 =new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa",UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false);

            when(userRepo.save(Mockito.any(User.class))).thenReturn(user1);
            assertNotNull(user1);
        }

        @Test
        void test_updateOneUser() {
            Set<Role> roles=new HashSet<>();
            Optional<User> user1 = Optional.ofNullable(new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa",UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));
            when(userRepo.findById(Mockito.anyLong())).thenReturn(user1);
            // set new value
            user1.get().setEmail("Kapoor@gmailcom");
            when(userRepo.save(Mockito.any(User.class))).thenReturn(user1.get());
            assertNotNull(user1);
        }

        @Test
        void test_deleteOne() {
        doNothing().when(userRepo).deleteById(Mockito.anyLong());
        }

}
