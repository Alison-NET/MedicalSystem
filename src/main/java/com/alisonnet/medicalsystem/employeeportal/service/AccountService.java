package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    Optional<Account> findById(int id);
    Optional<Account> findOne(Account account);
}
