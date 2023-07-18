package com.EmployeeOrgManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class EmployeeOrgManagementApplication {


	public static void main(String[] args) {

		SpringApplication.run(EmployeeOrgManagementApplication.class, args);


}}
