package com.vigilantex.employeeservice.services;

import com.vigilantex.employeeservice.entity.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient
{
    @GetMapping("micro/department/getdepartment?name={n}")
     Department getDepartment(@RequestParam("name") String n);
}
