package com.EmployeeSystemManagement.DataMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int employeeId;
    private String jobTitle;
    private String role;
    private String fullName;
    private String competency;
    private String Domain;
    private String phoneNumber;
    private String emailAddress;


}
