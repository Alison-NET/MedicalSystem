package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    Employee save(Employee employee);
    List<Employee> findAll();
}
