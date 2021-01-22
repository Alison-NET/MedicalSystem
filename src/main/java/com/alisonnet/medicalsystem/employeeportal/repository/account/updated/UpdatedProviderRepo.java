package com.alisonnet.medicalsystem.employeeportal.repository.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpdatedProviderRepo extends JpaRepository<UpdatedProvider, Integer> {
    Optional<UpdatedProvider> findTopByOrderByIdDesc();

}
