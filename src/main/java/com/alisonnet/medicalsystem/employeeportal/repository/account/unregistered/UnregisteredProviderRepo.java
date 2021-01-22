package com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnregisteredProviderRepo extends JpaRepository<UnregisteredProvider, Integer> {
    Optional<UnregisteredProvider> findTopByOrderByIdDesc();
}
