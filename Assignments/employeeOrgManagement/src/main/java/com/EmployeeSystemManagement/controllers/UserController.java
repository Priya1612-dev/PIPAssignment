package com.EmployeeSystemManagement.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.EmployeeSystemManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeSystemManagement.dao.UserRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserType;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import com.EmployeeSystemManagement.entities.Role;
//import com.EmployeeSystemManagementUtils.JwtTokenUtils;


@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		userService.createUser(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	@GetMapping("/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		User userByEmail=userService.getUserByEmail(email);
		return new ResponseEntity<>(userByEmail,HttpStatus.OK);
	
	}
	@GetMapping("/user/{userType}")
	public ResponseEntity<List<User>> getUserByUserType(@PathVariable("userType") UserType userType){
		List<User> userByUserType=userService.getUserByUserType(userType);
				return new ResponseEntity<>(userByUserType,HttpStatus.OK);
	
	}
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){
		User userByUserName=userService.getUserByUserName(userName);
		return new ResponseEntity<>(userByUserName,HttpStatus.OK);
	
	}
	@GetMapping("/user/{role}/count")
	public ResponseEntity<Integer> getUserCountForRole(@PathVariable("role") Role role){
		Integer userCountByRole=userService.getUserCountForRole(role);
		return new ResponseEntity<>(userCountByRole,HttpStatus.OK);
	
	}
	@GetMapping("/user/{role}")
	public ResponseEntity<List<User>> getUsersForRole(@PathVariable("role") Role role){
		List<User> userByRole=userService.getUsersForRole(role);
		return new ResponseEntity<>(userByRole,HttpStatus.OK);
	
	}
	  
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable("id") Long userId ) {
		User userById=userService.getUserbyId(userId);
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users=userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
			
		}
	
	@GetMapping("/{userId}/roles/permission")
	public ResponseEntity<List<Permission>> getUserPermissions(@PathVariable("userId") Long userId
			 ){
		List<Permission> permissions=userService.getUserPermissions(userId);

		return new ResponseEntity<>(permissions,HttpStatus.OK);
		
	}
	@GetMapping("/{userId}/permission")
	public ResponseEntity<List<Permission>> getUserRolePermission(@PathVariable("userId") Long userId){
		List<Permission> role_permission=userService.getUserRolePermission(userId);
		return new ResponseEntity<>(role_permission,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long userId){
			userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	}
	

