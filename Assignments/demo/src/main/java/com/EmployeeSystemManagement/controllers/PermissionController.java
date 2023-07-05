package com.EmployeeSystemManagement.controllers;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import com.EmployeeSystemManagement.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import com.EmployeeSystemManagement.entities.Role;

@RestController
public class PermissionController {
	@Autowired
	private PermissionService permissionService;

	@PostMapping("/user/role/permission")
	public ResponseEntity<Permission> createRolesWithPermission(@RequestBody Permission permission) {
		permissionService.createRolesWithPermission(permission);
		return new ResponseEntity<>(permission,HttpStatus.CREATED);
	}
	  
	@GetMapping("/user/role/permission/{id}")
	public ResponseEntity<Permission> getPermissionById(@PathVariable("id") Long permissionId ) {
		Permission permissionById= permissionService.getPermissionById(permissionId);
		return new ResponseEntity<>(permissionById,HttpStatus.OK);
	}
	@GetMapping("/user/role/permission/{name}")
	public ResponseEntity<Permission> getPermissionByName(@PathVariable("name") String name ) {
		Permission permissionByName= permissionService.getPermissionByName(name);
		return new ResponseEntity<>(permissionByName,HttpStatus.OK);
	}
	@GetMapping("/user/role/permission/{status}")
	public ResponseEntity<List<Permission>> getPermissionByStatus(@PathVariable("status") UserStatus status ) {
		List<Permission> permissionByStatus= permissionService.getPermissionByStatus(status);
		return new ResponseEntity<>(permissionByStatus,HttpStatus.OK);
	}
	
	@GetMapping("/{permissionId}/role")
	public ResponseEntity<Set<Role>> getMappedRoleWithPermission(@PathVariable("permissionId") Long permissionId ) {
		Set<Role> roles= permissionService.getMappedRoleWithPermission(permissionId);
		return new ResponseEntity<>(roles,HttpStatus.OK);
	}
	
	@GetMapping("/user/role/permission")
	public ResponseEntity<List<Permission>> getAllPermissions(){
		List<Permission> permissions= permissionService.getAllPermissions();
		return new ResponseEntity<>(permissions,HttpStatus.OK);
			
		}
	@GetMapping("/{permissionName}/user")
	public ResponseEntity<List<User>> getUserByPermissionName(@PathVariable("permissionName") String name){
		List<User> users= permissionService.getUserByPermissionName(name);
		return new ResponseEntity<>(users,HttpStatus.OK);
		}
	
	@DeleteMapping("/user/role/permission/{id}")
	public ResponseEntity<HttpStatus> deletePermission(@PathVariable("id") long permissionId){

			permissionService.deletePermission(permissionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
