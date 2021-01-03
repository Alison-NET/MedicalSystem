package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BasicEmployeeService {

    List<BasicEmployee> findAll();
    List<BasicEmployee> getUnapprovedEmployees();
    Optional<BasicEmployee> findById(int id);
    BasicEmployee save(BasicEmployee basicEmployee);
    void deleteById(int id);
    boolean personalEmailExists(String personalEmail);
}
