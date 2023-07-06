package com.EmployeeSystemManagement.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.EmployeeSystemManagement.enums.UserStatus;
import com.EmployeeSystemManagement.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
