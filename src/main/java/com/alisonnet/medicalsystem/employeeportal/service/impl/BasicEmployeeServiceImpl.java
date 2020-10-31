package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.repository.UnapprovedEmployeeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class BasicEmployeeServiceImpl implements BasicEmployeeService {

    UnapprovedEmployeeRepo unapprovedEmployeeRepo;

    @Override
    public BasicEmployee save(BasicEmployee employee) {
        return unapprovedEmployeeRepo.save(employee);
    }
}
