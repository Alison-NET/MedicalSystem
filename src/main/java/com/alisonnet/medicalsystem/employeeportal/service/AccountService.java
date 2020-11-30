package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account save(Account account);
}
