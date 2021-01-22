package com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnregisteredAccountRepo extends JpaRepository<UnregisteredAccount, Integer> {
    Optional<UnregisteredAccount> findTopByOrderByIdDesc();
}
