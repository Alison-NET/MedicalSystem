package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    Employee save(Employee employee);

}
