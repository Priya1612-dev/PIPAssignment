package com.EmployeeOrgManagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String jobTitle;
    private String role;
    private String fullName;
    private String competency;
    private String Domain;
    private String phoneNumber;
    private String emailAddress;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "org_Id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Organization org;


}
