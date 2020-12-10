package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Contract;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.ContractRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {

    ContractRepo contractRepo;

    @Override
    public Contract save(Contract contract) {
        return contractRepo.save(contract);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepo.findAll();
    }
}
