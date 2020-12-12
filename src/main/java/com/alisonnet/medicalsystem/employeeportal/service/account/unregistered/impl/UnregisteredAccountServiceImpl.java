package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredAccountRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnregisteredAccountServiceImpl implements UnregisteredAccountService {

    UnregisteredAccountRepo unregisteredAccountRepo;

    @Override
    public List<UnregisteredAccount> findAll() {
        return unregisteredAccountRepo.findAll();
    }

    @Override
    public UnregisteredAccount save(UnregisteredAccount unregisteredAccount) {
        return unregisteredAccountRepo.save(unregisteredAccount);
    }

    @Override
    public Optional<UnregisteredAccount> findById(int id) {
        return unregisteredAccountRepo.findById(id);
    }

    @Override
    public void remove(UnregisteredAccount unregisteredAccount) {
        unregisteredAccountRepo.delete(unregisteredAccount);
    }
}
