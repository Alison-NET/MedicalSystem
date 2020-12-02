package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import com.alisonnet.medicalsystem.employeeportal.repository.AccountRepo;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    AccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }
}
