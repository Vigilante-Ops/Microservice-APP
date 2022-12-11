package com.vigilantex.employeeservice.controllers;

import com.vigilantex.employeeservice.DTOS.employeeDTO;
import com.vigilantex.employeeservice.entity.Department;
import com.vigilantex.employeeservice.entity.Employee;
import com.vigilantex.employeeservice.repository.EmployeeRepo;
import com.vigilantex.employeeservice.services.APIClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.persistence.GeneratedValue;

@RestController
@RequestMapping("/micro/employee")
public class EmployeeController
{
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private APIClient apiClient;

    @PostMapping("save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
    {
          return new ResponseEntity<>(employeeRepo.save(employee), HttpStatus.CREATED);
    }
// @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @GetMapping("getEmployee")
    @Retry(fallbackMethod = "getDefaultDepartment",name = "${spring.application.name}")
    public ResponseEntity<employeeDTO> getEmployee(@RequestBody Employee employee)
    {
        System.out.println("inside get employee method");
       Department department= apiClient.getDepartment(employee.getDepartmentCode());
       Employee employeefetch=employeeRepo.findByEmailId(employee.getEmailId());
       employeeDTO e=new employeeDTO();
       e.setId(employeefetch.getId());
       e.setEmailId(employeefetch.getEmailId());
       e.setFirstName(employeefetch.getFirstName());
       e.setLastName(employeefetch.getLastName());
       e.getDepartment().setDepartmentCode(department.getDepartmentCode());
       e.getDepartment().setId(department.getId());
       e.getDepartment().setDepartmentName(department.getDepartmentName());
       e.getDepartment().setGetDepartmentDescription(department.getGetDepartmentDescription());

       return new ResponseEntity<>(e,HttpStatus.OK);

    }
    public ResponseEntity<employeeDTO> getDefaultDepartment(@RequestBody Employee employee,Throwable th)
    {
        System.out.println("inside get fallback method");

        Employee employeefetch=employeeRepo.findByEmailId(employee.getEmailId());
        employeeDTO e=new employeeDTO();
        e.setId(employeefetch.getId());
        e.setEmailId(employeefetch.getEmailId());
        e.setFirstName(employeefetch.getFirstName());
        e.setLastName(employeefetch.getLastName());
        e.getDepartment().setDepartmentCode("DEFAULT CODE");
        e.getDepartment().setId(111111L);
        e.getDepartment().setDepartmentName("DEFAULT NAME");
        e.getDepartment().setGetDepartmentDescription("DEFAULT DEPARTMENT DESCRIPTION");
        return new ResponseEntity<>(e,HttpStatus.OK);
    }
}
