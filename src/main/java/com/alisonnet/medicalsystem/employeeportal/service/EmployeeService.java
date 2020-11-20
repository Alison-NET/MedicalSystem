package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(int id);
    Optional<Employee> getActiveEmployee();
    List<Employee> gePossibleSupervisors(int forEmployeeId);

}
