package com.EmployeeSystemManagement.service;

import com.EmployeeSystemManagement.dao.RoleRepository;
import com.EmployeeSystemManagement.entities.Role;
import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public Role createRoleWithPermision(Role role) {
        return roleRepo.save(role);

    }

    public List<Role> getRoleByNameAndStatus(String name,UserStatus status) {
        return roleRepo.findRoleByNameAndStatus(name, status);
    }

    public Role getRoleById( Long roleId ) {
        return roleRepo.findById(roleId)
                .orElseThrow(()->new ResourceNotFoundException("Not found role with id"+roleId));

    }


    public List<Role> getAllRoles(){

        return roleRepo.findAll();
    }

    public void deleteRole(long roleId){
        if(roleRepo.existsById(roleId)) {
            roleRepo.deleteById(roleId);
        }
        roleRepo.deleteById(roleId);
    }
}
