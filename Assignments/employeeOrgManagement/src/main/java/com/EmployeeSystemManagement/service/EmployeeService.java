package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.DataMapper.Employee;
import com.EmployeeSystemManagement.dto.EmployeeList;
import com.EmployeeSystemManagement.jwtUtils.JwtTokenUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.example.Employees;
import org.example.SFTPConnect;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.example.ConvertUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
@Slf4j
public class EmployeeService {
    @Value("${SFTP_USERNAME}")
    private String sftpUsername;
    @Value("${SFTP_PORT}")
    private int sftpPort;
    @Value("${SFTP_PASSWORD}")
    private String sftpPassword;
    @Value("${SFTP_HOST}")
    private String sftpHost;
    @Value("${SFTP_DIRECTORY}")
    private String sftpDirectory;
    @Value("${SECOND_URL}")
    private String url;

    @Autowired
    private JwtTokenUtil tokenUtil;

    public void loadEmployee( /*String authToken,*/String fileName

    ) {
        SFTPConnect sftpConnect=new SFTPConnect();
        ConvertUtil convertUtil=new ConvertUtil();

        String fileContent = sftpConnect.readFileFromSftp(sftpHost, sftpPort, sftpUsername, sftpPassword, sftpDirectory, fileName);
        Employee employee=new Employee();
        List<Employees> employees =convertUtil.convertJsonToPojoEmployee(fileContent);
        BeanUtils.copyProperties(employees,employee);
        EmployeeList employeeList=new EmployeeList();
        employeeList.setEmployeeList(employees);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.set("Authorization","Bearer"+authToken);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity employeeList = new HttpEntity<>(employees);
        System.out.println(url);

        restTemplate.exchange("http://localhost:8282/saveEmployee", HttpMethod.POST, employeeList, String.class);
       // return ResponseEntity<>()

      //  restTemplate.postForObject()

    }
   }




