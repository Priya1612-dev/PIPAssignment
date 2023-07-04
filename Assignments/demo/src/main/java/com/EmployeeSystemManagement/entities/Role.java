package com.EmployeeSystemManagement.entities;


import java.util.Set;

import com.EmployeeSystemManagement.enums.UserStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid")
	private Long roleId;
	
	private String name;

    private String description;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    
    private String details;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roles")
	private Set<User> users;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission",
            joinColumns = { @JoinColumn(name = "roleid") },
            inverseJoinColumns = { @JoinColumn(name = "permissionid") })
    private Set<Permission> allpermission;
	
	public void addUser(User user) {
    	this.users.add(user);
    	user.getRoles().add(this);
    }
    public void removeUser(User user) {
    	this.users.remove(user);
    	user.getRoles().remove(this);
    }
    
	public void addPermission(Permission permission) {
		this.allpermission.add(permission);
		permission.getRoles().add(this);
	}
	public void removePermission(Permission permission) {
		this.allpermission.remove(permission);
		permission.getRoles().remove(this);
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Permission> getAllpermission() {
		return allpermission;
	}

	public void setAllpermission(Set<Permission> allpermission) {
		this.allpermission = allpermission;
	}
}
