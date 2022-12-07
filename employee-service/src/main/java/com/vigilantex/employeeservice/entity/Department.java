package com.vigilantex.employeeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter@Setter
public class Department
{
    private Long id;

    private String departmentName;

    private String getDepartmentDescription;

    private String departmentCode;
}
