package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import org.springframework.stereotype.Service;

@Service
public interface BasicEmployeeService {
    BasicEmployee save(BasicEmployee employee);
}
