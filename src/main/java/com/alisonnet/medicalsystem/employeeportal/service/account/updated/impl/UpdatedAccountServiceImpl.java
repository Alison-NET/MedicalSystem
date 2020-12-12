package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedAccountRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdatedAccountServiceImpl implements UpdatedAccountService {

    UpdatedAccountRepo updatedAccountRepo;

    @Override
    public List<UpdatedAccount> findAll() {
        return updatedAccountRepo.findAll();
    }

    @Override
    public UpdatedAccount save(UpdatedAccount updatedAccount) {
        return updatedAccountRepo.save(updatedAccount);
    }

    @Override
    public Optional<UpdatedAccount> findById(int id) {
        return updatedAccountRepo.findById(id);
    }

    @Override
    public void remove(UpdatedAccount updatedAccount) {
        updatedAccountRepo.delete(updatedAccount);
    }
}
