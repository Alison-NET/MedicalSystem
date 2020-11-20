package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Department;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.DepartmentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepo departmentRepo;

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public boolean hasDepartmentChief(Department department) {
        return department.getJobPositions().stream()
                .anyMatch(jobPosition -> jobPosition.getEmployees()
                        .contains(jobPosition.getEmployees().stream().
                                filter(Employee::isDepartmentChief).findFirst().orElse(null)));
    }

    @Override
    public List<Department> getDepartmentsWithoutChiefs() {
        return departmentRepo.findAll().stream()
                .filter(department -> !hasDepartmentChief(department))
                .collect(Collectors.toList());
    }
}
