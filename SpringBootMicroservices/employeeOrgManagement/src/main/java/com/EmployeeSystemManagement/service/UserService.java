package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dao.UserRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserType;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    public User createUser(User user) {
       return userRepo.save(user);
    }
    public User getUserByEmail( String email){
        return userRepo.findByEmail(email);

    }
    @GetMapping("/user/{userType}")
    public List<User> getUserByUserType(UserType userType){
        return userRepo.findByUserType(userType);

    }

    public User getUserByUserName( String userName){
        return userRepo.findByUsername(userName).orElseThrow(()->new ResourceNotFoundException("Username not found"+userName));

    }

    public Integer getUserCountForRole(Role role){
        return userRepo.findByRoles(role).size();

    }

    public List<User> getUsersForRole(Role role){
        return userRepo.findByRoles(role);

    }


    public User getUserbyId(Long userId ) {
        return userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("Not found user with id"+userId));

    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public List<Permission> getUserPermissions( Long userId){
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found with id"+userId));

        Set<Role> role=user.getRoles();
        return role.stream().flatMap(x->x.getAllpermission().stream())
                .distinct().collect(Collectors.toList());

    }

    public List<Permission> getUserRolePermission(Long userId){
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found with id"+userId));

        Set<Role> _roles=user.getRoles();
        return _roles.stream().flatMap(x->x.getAllpermission().stream())
                .distinct().collect(Collectors.toList());

    }


    public void deleteUser(long userId){
        if(userRepo.existsById(userId)) {
            User user=userRepo.findById(userId).orElseThrow(
                    ()->new ResourceNotFoundException("Not found user with id"+userId));
            user.setDeleted(true);

        }
    }


}
