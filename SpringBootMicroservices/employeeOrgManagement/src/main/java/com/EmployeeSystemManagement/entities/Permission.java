package com.EmployeeSystemManagement.entities;

import java.util.Set;

import com.EmployeeSystemManagement.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "permissionId")
	private Long permissionId;
	@Column(unique=true)
    private String name;
    
    private String description;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "allpermission")
	private Set<Role> roles;

    
	
    
    
}
