package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import org.springframework.stereotype.Service;


@Service
public interface EmployeeTreeService {
    void removeFromDepartmentRelations(Employee employee);
    void tryUpdateRelations(Employee employee);
    boolean hasInSubordinates(Employee supervisor, Employee subordinate);

}
