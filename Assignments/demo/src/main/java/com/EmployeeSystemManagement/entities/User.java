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
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(unique = true)
    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "username should be between 3 and 20 characters")
    private String username;
    private String name;
    @Column(unique = true)
    private String email;
    @NotBlank(message = "password is required")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$",
            message = "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters")
    private String password;
    private String salt;
    @Enumerated(EnumType.STRING)
    @Column(name = "userType")
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "passwordExpiry")
    private Date passwordExpiry;
    @Column(name = "lastLoginDate")
    private Date lastLoginDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
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

}
