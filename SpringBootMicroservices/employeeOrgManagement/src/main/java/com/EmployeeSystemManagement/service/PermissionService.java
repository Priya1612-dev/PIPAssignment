package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dao.PermissionRepository;
import com.EmployeeSystemManagement.entities.Permission;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.entities.User;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepo;

    public Permission createRolesWithPermission(Permission permission) {
        permissionRepo.save(permission);
        return permission;
    }

    public Permission getPermissionById(Long permissionId ) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(()->new ResourceNotFoundException("Not found permission with id"+permissionId));
    }

    public Permission getPermissionByName(String name ) {
        return permissionRepo.findPermissionByName(name);
    }

    public List<Permission> getPermissionByStatus(UserStatus status ) {
        return permissionRepo.findPermissionByStatus(status);
    }


    public Set<Role> getMappedRoleWithPermission(Long permissionId ) {
        Permission permissionById=permissionRepo.findById(permissionId)
                .orElseThrow(()->new ResourceNotFoundException("Permission not found with id"+permissionId));
        return permissionById.getRoles();
    }

    public List<Permission> getAllPermissions(){
        return permissionRepo.findAll();
    }

    public List<User> getUserByPermissionName( String name){
        Permission permission=permissionRepo.findPermissionByName(name);
        Set<Role> roles=permission.getRoles();
       return roles.stream().flatMap(role->role.getUsers().stream())
                .distinct().collect(Collectors.toList());
    }

    public void deletePermission( long permissionId){
        permissionRepo.deleteById(permissionId);
    }
}
