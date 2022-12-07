package com.vigilantex.employeeservice.DTOS;

import com.vigilantex.employeeservice.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class employeeDTO
{
    private Long id;

    private String firstName;

    private String lastName;

    private String emailId;

    private String departmentCode;

    private Department department=new Department();
}
