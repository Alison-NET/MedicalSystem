package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findFirstByCredentials(Credentials credentials);
}
