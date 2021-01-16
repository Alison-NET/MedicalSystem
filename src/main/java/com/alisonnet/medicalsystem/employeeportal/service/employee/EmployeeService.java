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
    boolean exists(Employee employee);
    Optional<Employee> getActiveEmployee();
    List<Employee> getPossibleSupervisors(Employee employee);
    boolean isInHRDepartment(Employee employee);
    boolean isInAdminDepartment(Employee employee);
    boolean canBeEdited(Employee employeeToEdit, Employee activeEmployee);
    void updateRelationsIfNeeded(Employee employee);

}
