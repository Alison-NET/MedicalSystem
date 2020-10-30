package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, String> {
}
