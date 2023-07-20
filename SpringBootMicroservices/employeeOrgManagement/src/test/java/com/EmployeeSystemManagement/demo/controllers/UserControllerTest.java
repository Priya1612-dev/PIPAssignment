package com.EmployeeSystemManagement.demo.controllers;

import com.EmployeeSystemManagement.controllers.UserController;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.enums.UserType;
import com.EmployeeSystemManagement.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        private UserService userService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void test_findAll() throws Exception {
            List<User> userList = new ArrayList<>();
            Set<Role> roles=new HashSet<>();
            userList.add(new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa", UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));

            userList.add(new User(2L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa", UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false));

            when(userService.getAllUsers()).thenReturn(userList);
            assertNotNull(userList);

            // calling rest api
            ResultActions response = mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON));

            // then - verify the output
            response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(userList.size())));
        }

        @Test
        void test_findOneEmployee() throws Exception {
            Long id = 1L;
            Set<Role> roles=new HashSet<>();
            User e1 = new User(1L,"priya123","xyz","priya@gmail.com","Abc1234@","aaa", UserType.ACTIVE,
                    UserStatus.ADMIN,new Date(2025,12,11),new Date(2035,12,11),roles,false);


            when(userService.getUserbyId(id)).thenReturn(e1);
            assertNotNull(e1);

            // calling rest api
            ResultActions response = mockMvc.perform(get("/user/{id}", id).contentType(MediaType.APPLICATION_JSON));

            // then - verify the output
            response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
                    .andExpect(jsonPath("$.lastName", is(e1.getLastName())))
                    .andExpect(jsonPath("$.email", is(e1.getEmail())));
        }

        @Test
        void test_deleteOne() throws Exception {
            Long id = 101L;
            String msg = "Employee with id -->" + id + " successfully deleted";
            final OngoingStubbing<T> tOngoingStubbing = when(userService.deleteUser(Mockito.anyLong())).thenReturn(msg);

            // calling rest api
            ResultActions response = mockMvc
                    .perform(delete("/user/{id}", id).contentType(MediaType.APPLICATION_JSON));

            // then - verify the output
            response.andExpect(status().isOk()).andDo(print());
        }
//
//        @Test
//        void test_createTask() throws JsonProcessingException, Exception {
//            Long id = 101L;
//            Employee e1 = new Employee(id, "Aman", "Sharma", "aman@gmail.com", "Manager");
//
//            when(employeeService.createTask(Mockito.any(Employee.class))).thenReturn(e1);
//
//            // calling rest api
//            ResultActions response = mockMvc.perform(post("/emp/create").contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(e1)));
//
//            // then - verify the output
//            response.andDo(print()).andExpect(status().isCreated())
//                    .andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
//                    .andExpect(jsonPath("$.lastName", is(e1.getLastName())))
//                    .andExpect(jsonPath("$.email", is(e1.getEmail())));
//
//        }
//
//        @Test
//        void test_updateOneEmployee() throws JsonProcessingException, Exception {
//            Long id = 102L;
//            Employee oldEmp = new Employee(102L, "Suman", "Kapoor", "suman@gmail.com", "Clerk");
//            Employee e1 = new Employee(102L, "Suman", "Kharatri", "suman013@gmail.com", "Manager");
//
//            // finding existing employee details having id 102 before update
//            when(employeeService.findOneEmployee(id)).thenReturn(oldEmp);
//
//            // now update the same employee details
//            when(employeeService.updateOneEmployee(Mockito.anyLong(), Mockito.any(Employee.class))).thenReturn(e1);
//
//            ResultActions response = mockMvc.perform(put("/update/{id}", id).contentType(MediaType.APPLICATION_JSON)
//                    .content(objectMapper.writeValueAsString(e1)));
//
//            // then - verify the output
//            response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.firstName", is(e1.getFirstName())))
//                    .andExpect(jsonPath("$.lastName", is(e1.getLastName())))
//                    .andExpect(jsonPath("$.email", is(e1.getEmail())));
//
//        }




}
