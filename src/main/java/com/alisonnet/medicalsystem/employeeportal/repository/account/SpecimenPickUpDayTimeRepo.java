package com.alisonnet.medicalsystem.employeeportal.repository.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecimenPickUpDayTimeRepo extends JpaRepository<SpecimenPickUpDayTime, Integer> {
    Optional<SpecimenPickUpDayTime> findTopByOrderByIdDesc();
}
