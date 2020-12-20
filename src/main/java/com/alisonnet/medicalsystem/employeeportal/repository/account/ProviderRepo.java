package com.alisonnet.medicalsystem.employeeportal.repository.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<Provider, Integer> {
}