package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(int id);
    Account save(Account account);
    Account createAccount();
    void fillUniqueIds(Account account);
    void fillUniqueEmptyIds(Account account);
    void matchIdsGenerateUniqueIfNeeded(Account from, Account to);
    void fillNeededData(Account account);
    void remove(Account account);
    int getMaxId();
}
