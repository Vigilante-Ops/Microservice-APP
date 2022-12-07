package com.vigilantex.departmentservice.controllers;

import com.vigilantex.departmentservice.entity.Department;
import com.vigilantex.departmentservice.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/micro/department")
public class DepartmentController
{


    @Autowired
    private DepartmentRepo departmentRepo;

    @PostMapping("save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department)
    {
        Department d=departmentRepo.save(department);
       return new ResponseEntity<>(d, HttpStatus.CREATED);
    }
    @GetMapping("getdepartment")
    public ResponseEntity<Department> getDepartment(@RequestParam("name") String name)
    {
        Department d=departmentRepo.findByDepartmentCode(name);
        return new ResponseEntity<>(d, HttpStatus.OK);
    }


}
