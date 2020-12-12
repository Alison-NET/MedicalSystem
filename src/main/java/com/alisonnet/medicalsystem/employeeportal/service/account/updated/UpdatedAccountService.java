package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UpdatedAccountService {
    List<UpdatedAccount> findAll();
    UpdatedAccount save(UpdatedAccount updatedAccount);
    Optional<UpdatedAccount> findById(int id);
    void remove(UpdatedAccount updatedAccount);
}
