package com.vigilantex.departmentservice.repository;

import com.vigilantex.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    public Department findByDepartmentCode(String departmentcode);
}
