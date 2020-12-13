package com.alisonnet.medicalsystem.employeeportal.repository.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
}
