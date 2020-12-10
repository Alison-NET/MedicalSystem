package com.alisonnet.medicalsystem.employeeportal.repository.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickUpTimeRepo extends JpaRepository<PickUpTime, Integer> {
}
