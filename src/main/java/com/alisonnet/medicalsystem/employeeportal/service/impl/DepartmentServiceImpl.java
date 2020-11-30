package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Department;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.DepartmentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepo departmentRepo;

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public List<Department> getDepartmentsWithoutChiefs() {
        return departmentRepo.findAll().stream()
                .filter(department -> department.getChiefJobPosition().getEmployees().isEmpty())
                .collect(Collectors.toList());
    }
}
