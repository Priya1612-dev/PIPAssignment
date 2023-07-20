package com.EmployeeSystemManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.enums.UserStatus;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>  {

	List<Role> findRoleByNameAndStatus(String name, UserStatus status);

}
