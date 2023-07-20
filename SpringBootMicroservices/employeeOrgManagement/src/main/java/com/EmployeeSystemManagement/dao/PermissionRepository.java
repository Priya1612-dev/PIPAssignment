package com.EmployeeSystemManagement.dao;

import java.util.List;

import com.EmployeeSystemManagement.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeSystemManagement.enums.UserStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Permission findPermissionByName(String name);
	
	List<Permission> findPermissionByStatus(UserStatus status);
}
