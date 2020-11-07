package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BasicEmployeeService {

    BasicEmployee registerNewBasicEmployee(BasicEmployee basicEmployee);

    List<BasicEmployee> findAll();

    Optional<BasicEmployee> findById(int id);

    BasicEmployee save(BasicEmployee basicEmployee);

    void deleteById(int id);

    boolean personalEmailExists(String personalEmail);
}
