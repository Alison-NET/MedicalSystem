package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Department;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.DepartmentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DepartmentService;
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
    public List<Department> findAllByOrderByNameAsc() {
        return departmentRepo.findAllByOrderByNameAsc();
    }

    @Override
    public List<Department> getDepartmentsWithoutChiefs() {
        return departmentRepo.findAll().stream()
                .filter(department -> department.getChiefJobPosition().getEmployees().isEmpty())
                .collect(Collectors.toList());
    }
}
