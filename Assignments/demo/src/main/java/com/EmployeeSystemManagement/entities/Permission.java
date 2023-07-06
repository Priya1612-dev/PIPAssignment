package com.EmployeeSystemManagement.entities;

import java.util.Set;

import com.EmployeeSystemManagement.enums.UserStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
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
