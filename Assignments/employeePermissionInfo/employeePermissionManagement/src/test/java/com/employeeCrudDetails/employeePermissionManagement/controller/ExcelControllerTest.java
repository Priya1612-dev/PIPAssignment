package com.employeeCrudDetails.employeePermissionManagement.controller;

import com.employeeCrudDetails.employeePermissionManagement.entities.EmployeeDetails;
import com.employeeCrudDetails.employeePermissionManagement.service.ExcelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ExcelController.class)
public class ExcelControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        private ExcelService excelService;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void test_readExcel() throws Exception {
            List<EmployeeDetails> empList = new ArrayList<>();
            when(excelService.save()).thenReturn(empList);
            assertNotNull(empList);

            ResultActions response = mockMvc.perform(get("/readExcel").contentType(MediaType.APPLICATION_JSON));

            response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(empList.size())));

        }



}
