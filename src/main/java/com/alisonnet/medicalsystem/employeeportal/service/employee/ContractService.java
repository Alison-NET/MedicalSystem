package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Contract;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractService {

    Contract save(Contract contract);
    List<Contract> findAll();
}
