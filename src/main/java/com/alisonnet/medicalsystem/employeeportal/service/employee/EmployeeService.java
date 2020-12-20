package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(int id);
    Optional<Employee> getActiveEmployee();
    List<Employee> getPossibleSupervisors(int forEmployeeId);
    boolean isInHRDepartment(Employee employee);
    boolean isInAdminDepartment(Employee employee);
    void updateRelationsIfNeeded(Employee employee);

}