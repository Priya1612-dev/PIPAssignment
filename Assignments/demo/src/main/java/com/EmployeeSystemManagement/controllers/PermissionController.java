package com.EmployeeSystemManagement.controllers;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeSystemManagement.dao.PermissionRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import com.EmployeeSystemManagement.entities.Role;

@RestController
public class PermissionController {
	@Autowired
	private PermissionRepository permissionRepo;

	@PostMapping("/user/role/permission")
	public ResponseEntity<Permission> createRolesWithPermmission(@RequestBody Permission permission) {
		permissionRepo.save(permission);
		return new ResponseEntity<>(permission,HttpStatus.CREATED);
	}
	  
	@GetMapping("/user/role/permission/{id}")
	public ResponseEntity<Permission> getPermissionById(@PathVariable("id") Long permissionId ) {
		Permission permissionById=permissionRepo.findById(permissionId)
				.orElseThrow(()->new ResourceNotFoundException("Not found permission with id"+permissionId));
		return new ResponseEntity<>(permissionById,HttpStatus.OK);
	}
	@GetMapping("/user/role/permission/{name}")
	public ResponseEntity<Permission> getPermissionByName(@PathVariable("name") String name ) {
		Permission permissionByName=permissionRepo.findPermissionByName(name);
		return new ResponseEntity<>(permissionByName,HttpStatus.OK);
	}
	@GetMapping("/user/role/permission/{status}")
	public ResponseEntity<List<Permission>> getPermissionByStatus(@PathVariable("status") UserStatus status ) {
		List<Permission> permissionByStatus=permissionRepo.findPermissionByStatus(status);
		return new ResponseEntity<>(permissionByStatus,HttpStatus.OK);
	}
	
	@GetMapping("/{permissionId}/role")
	public ResponseEntity<Set<Role>> getMappedRoleWithPermission(@PathVariable("permissionId") Long permissionId ) {
		Permission permissionById=permissionRepo.findById(permissionId)
				.orElseThrow(()->new ResourceNotFoundException("Permission not found with id"+permissionId));
			Set<Role> roles=permissionById.getRoles();
		return new ResponseEntity<>(roles,HttpStatus.OK);
	}
	
	@GetMapping("/user/role/permission")
	public ResponseEntity<List<Permission>> getAllPermissions(){
		List<Permission> permissions=permissionRepo.findAll();
		if(permissions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(permissions,HttpStatus.OK);
			
		}
	@GetMapping("/{permissionName}/user")
	public ResponseEntity<List<User>> getUserByPermissionName(@PathVariable("permissionName") String name){
		Permission permission=permissionRepo.findPermissionByName(name);
		Set<Role> roles=permission.getRoles();
		List<User> users=roles.stream().flatMap(role->role.getUsers().stream())
				.distinct().collect(Collectors.toList());
		return new ResponseEntity<>(users,HttpStatus.OK);
		}
	
	@DeleteMapping("/user/role/permission/{id}")
	public ResponseEntity<HttpStatus> deletePermission(@PathVariable("id") long permissionId){
		if(permissionRepo.existsById(permissionId)) {
			permissionRepo.deleteById(permissionId);
		}
		permissionRepo.deleteById(permissionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
