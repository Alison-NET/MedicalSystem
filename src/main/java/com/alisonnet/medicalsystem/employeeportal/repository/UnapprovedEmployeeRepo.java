package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnapprovedEmployeeRepo extends JpaRepository<BasicEmployee, Integer> {
}
