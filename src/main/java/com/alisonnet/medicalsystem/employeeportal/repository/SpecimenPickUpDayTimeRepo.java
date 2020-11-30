package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.SpecimenPickUpDayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecimenPickUpDayTimeRepo extends JpaRepository<SpecimenPickUpDayTime, Integer> {
}
