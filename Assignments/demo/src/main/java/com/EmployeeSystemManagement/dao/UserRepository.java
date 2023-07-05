package com.EmployeeSystemManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserType;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByEmail(String email);
	
	List<User> findUserByUserType(UserType userType);
	
	User findUserByUsername(String userName);
	
	List<User> findUserByRoles(Role role);
	
	
	
	}
