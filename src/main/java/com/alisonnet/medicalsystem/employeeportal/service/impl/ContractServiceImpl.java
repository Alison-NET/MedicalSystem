package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Contract;
import com.alisonnet.medicalsystem.employeeportal.repository.ContractRepo;
import com.alisonnet.medicalsystem.employeeportal.service.ContractService;
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
