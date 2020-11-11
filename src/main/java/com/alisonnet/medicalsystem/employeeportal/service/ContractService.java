package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Contract;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContractService {

    Contract save(Contract contract);
    List<Contract> findAll();
}
