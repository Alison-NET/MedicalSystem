package com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnregisteredPickUpTimeRepo extends JpaRepository<UnregisteredPickUpTime, Integer> {
    Optional<UnregisteredPickUpTime> findTopByOrderByIdDesc();
}
