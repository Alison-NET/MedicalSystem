package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    void fillUniqueIds(Account account);
    void fillNeededData(Account account);
    Optional<Account> findById(int id);
    void remove(Account account);
}
