package com.EmployeeSystemManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginDetails implements  Serializable{
    private String jwtToken;
    private String username;
}
