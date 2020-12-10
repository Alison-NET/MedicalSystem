package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> findAll();
    List<Department> findAllByOrderByNameAsc();
    List<Department> getDepartmentsWithoutChiefs();
}
