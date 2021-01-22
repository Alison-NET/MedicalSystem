package com.alisonnet.medicalsystem.employeeportal.repository.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpdatedAccountRepo extends JpaRepository<UpdatedAccount, Integer> {
    Optional<UpdatedAccount> findTopByOrderByIdDesc();
}
