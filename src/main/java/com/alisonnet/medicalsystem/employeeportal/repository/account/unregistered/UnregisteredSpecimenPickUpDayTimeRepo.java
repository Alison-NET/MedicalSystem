package com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnregisteredSpecimenPickUpDayTimeRepo extends JpaRepository<UnregisteredSpecimenPickUpDayTime, Integer> {
    Optional<UnregisteredSpecimenPickUpDayTime> findTopByOrderByIdDesc();
}
