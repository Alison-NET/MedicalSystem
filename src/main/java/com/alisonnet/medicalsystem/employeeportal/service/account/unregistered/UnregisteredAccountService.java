package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UnregisteredAccountService {
    List<UnregisteredAccount> findAll();
    UnregisteredAccount save(UnregisteredAccount unregisteredAccount);
    Optional<UnregisteredAccount> findById(int id);
    void remove(UnregisteredAccount unregisteredAccount);
}
