package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UpdatedAccountService {
    List<UpdatedAccount> findAll();
    UpdatedAccount save(UpdatedAccount updatedAccount);
    Optional<UpdatedAccount> findById(int id);
    void fillUniqueIds(UpdatedAccount updatedAccount);
    void fillNeededData(UpdatedAccount updatedAccount);
    void remove(UpdatedAccount updatedAccount);
    int getMaxId();
}
