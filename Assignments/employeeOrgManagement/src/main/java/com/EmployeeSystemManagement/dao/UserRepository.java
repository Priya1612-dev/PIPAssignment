package com.EmployeeSystemManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	User findByEmail(String email);
	
	List<User> findByUserType(UserType userType);
	
	User findByUsername(String userName);
	
	List<User> findByRoles(Role role);



}
