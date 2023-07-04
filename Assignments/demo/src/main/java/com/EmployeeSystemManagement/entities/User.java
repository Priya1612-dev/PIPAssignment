package com.EmployeeSystemManagement.entities;

import java.util.Date;
import java.util.Set;

import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid")
	private Long userId;
	@Column(unique=true,name="username")
	@NotBlank(message="username is required")
	@Size(min=3,max=20,message="username should be between 3 and 20 characters")
	private String username;
	private String name;
	@Column(unique=true)
	private String email;
	@NotBlank(message="password is required")
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
			message ="Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters")
	private String password;
	private String salt;
	@Enumerated(EnumType.STRING)
	@Column(name="usertype")
	private UserType userType;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column(name="passwordexpiry")
	private Date passwordExpiry;
	@Column(name="lastlogindate")
	private Date lastLoginDate;
	 @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_role",
	            joinColumns = { @JoinColumn(name = "userid") },
	            inverseJoinColumns = { @JoinColumn(name = "roleid") })
	private Set<Role> roles;
	private boolean deleted;
	 public void addRole(Role role) {
		 this.roles.add(role);
		 role.getUsers().add(this);
		 
	 }
	 public void removeRole(Role role) {
		 this.roles.remove(role);
		 role.getUsers().remove(this);
	 }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getPasswordExpiry() {
		return passwordExpiry;
	}

	public void setPasswordExpiry(Date passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
