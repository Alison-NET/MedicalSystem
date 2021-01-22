package com.alisonnet.medicalsystem.employeeportal.repository.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpdatedSpecimenPickUpDayTimeRepo extends JpaRepository<UpdatedSpecimenPickUpDayTime, Integer> {
    Optional<UpdatedSpecimenPickUpDayTime> findTopByOrderByIdDesc();
}
