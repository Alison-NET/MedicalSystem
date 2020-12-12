package com.alisonnet.medicalsystem.employeeportal.repository.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatedPickUpTimeRepo extends JpaRepository<UpdatedPickUpTime, Integer> {
}
