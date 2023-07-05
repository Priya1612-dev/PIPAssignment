package com.EmployeeSystemManagement.controllers;

import java.util.List;
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

import com.EmployeeSystemManagement.dao.RoleRepository;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;

@RestController
public class RoleController {
	@Autowired
	private RoleRepository roleRepo;

	@PostMapping("/user/role")
	public ResponseEntity<Role> createRoleWithPermmision(@RequestBody Role role) {
		roleRepo.save(role);
		return new ResponseEntity<>(role,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/role/{name}/{status}")
	public ResponseEntity<List<Role>> getRoleByNameAndStatus(@PathVariable("name") String name,
			@PathVariable("status") UserStatus status) {
		List<Role> roleByNameAndStatus=roleRepo.findRoleByNameAndStatus(name, status);
				return new ResponseEntity<>(roleByNameAndStatus,HttpStatus.OK);
	}
	  
	@GetMapping("/user/role/{id}")
	public ResponseEntity<Role> getRoleById(@PathVariable("id") Long roleId ) {
		Role roleById=roleRepo.findById(roleId)
				.orElseThrow(()->new ResourceNotFoundException("Not found role with id"+roleId));
		return new ResponseEntity<>(roleById,HttpStatus.OK);
	}
	
	@GetMapping("/user/role")
	public ResponseEntity<List<Role>> getAllRoles(){
		List<Role> roles=roleRepo.findAll();
		if(roles.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(roles,HttpStatus.OK);
			
		}
	
	@DeleteMapping("/user/role/{id}")
	public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") long roleId){
		if(roleRepo.existsById(roleId)) {
			roleRepo.deleteById(roleId);
		}
		roleRepo.deleteById(roleId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
