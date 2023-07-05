package com.EmployeeSystemManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.enums.UserStatus;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findPermissionByName(String name);
	
	List<Permission> findPermissionByStatus(UserStatus status);
}
