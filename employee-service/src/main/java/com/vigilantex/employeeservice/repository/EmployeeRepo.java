package com.vigilantex.employeeservice.repository;

import com.vigilantex.employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    public Employee findByEmailId(String email);
}
