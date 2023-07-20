package com.EmployeeSystemManagement.entities;

import com.EmployeeSystemManagement.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        @Column(name = "roleId")
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
                joinColumns = { @JoinColumn(name = "roleId") },
                inverseJoinColumns = { @JoinColumn(name = "permissionId") })
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


    }
