package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.EmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }
}
