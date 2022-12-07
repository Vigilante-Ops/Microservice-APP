package com.vigilantex.employeeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter@Getter
@Table(name = "employees")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String emailId;

    @Transient
    private String departmentCode;
}
