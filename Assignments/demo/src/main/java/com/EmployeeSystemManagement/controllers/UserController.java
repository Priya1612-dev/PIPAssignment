package com.EmployeeSystemManagement.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	private UserRepository userRepo;
//	@Autowired 
//	private JwtTokenUtils jwtUtil;
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@PostMapping("/user")
	public ResponseEntity<User> createUserWithRoles(@RequestBody User user) {
		userRepo.save(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	@GetMapping("/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
		User userByEmail=userRepo.findUserByEmail(email);
		return new ResponseEntity<>(userByEmail,HttpStatus.OK);
	
	}
	@GetMapping("/user/{userType}")
	public ResponseEntity<List<User>> getUserByUserType(@PathVariable("userType") UserType userType){
		List<User> userByUserType=userRepo.findUserByUserType(userType);
				return new ResponseEntity<>(userByUserType,HttpStatus.OK);
	
	}
	@GetMapping("/user/{userName}")
	public ResponseEntity<User> getUserByUserName(@PathVariable("userName") String userName){
		User userByUserName=userRepo.findUserByUsername(userName);
		return new ResponseEntity<>(userByUserName,HttpStatus.OK);
	
	}
	@GetMapping("/user/{role}/count")
	public ResponseEntity<Integer> getUserCountForRole(@PathVariable("role") Role role){
		Integer userCountByRole=userRepo.findUserByRoles(role).size();
		return new ResponseEntity<>(userCountByRole,HttpStatus.OK);
	
	}
	@GetMapping("/user/{role}")
	public ResponseEntity<List<User>> getUsersForRole(@PathVariable("role") Role role){
		List<User> userByRole=userRepo.findUserByRoles(role);
		return new ResponseEntity<>(userByRole,HttpStatus.OK);
	
	}
	  
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserbyId(@PathVariable("id") Long userId ) {
		User userById=userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("Not found user with id"+userId));
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users=userRepo.findAll();
		if(users.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users,HttpStatus.OK);
			
		}
	
	@GetMapping("/{userId}/roles/permission")
	public ResponseEntity<List<Permission>> getUserPermissions(@PathVariable("userId") Long userId
			 ){
		User user=userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user not found with id"+userId));
		
		Set<Role> role=user.getRoles();
		List<Permission> role_permission=role.stream().flatMap(x->x.getAllpermission().stream())
		.distinct().collect(Collectors.toList());
		return new ResponseEntity<>(role_permission,HttpStatus.OK);
		
	}
	@GetMapping("/{userId}/permission")
	public ResponseEntity<List<Permission>> getUserRolePermission(@PathVariable("userId") Long userId){
		User user=userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("user not found with id"+userId));
		
		Set<Role> _roles=user.getRoles();
		List<Permission> role_permission=_roles.stream().flatMap(x->x.getAllpermission().stream())
		.distinct().collect(Collectors.toList());
		return new ResponseEntity<>(role_permission,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long userId){
		if(userRepo.existsById(userId)) {
			User user=userRepo.findById(userId).orElseThrow(
					()->new ResourceNotFoundException("Not found user with id"+userId));
			user.setDeleted(true);
			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@GetMapping("/login")
//	public String loginUser(@RequestBody UserLogin user){
//		try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("invalid username/password");
//        }
//        return jwtUtil.generateToken(user.getUserName());
//    }
	}
	

