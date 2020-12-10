package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {
    List<Department> findAllByOrderByNameAsc();
}
