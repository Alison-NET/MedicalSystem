package com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpDayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnregisteredPickUpDayOfWeekRepo extends JpaRepository<UnregisteredPickUpDayOfWeek, Integer> {
    List<UnregisteredPickUpDayOfWeek> findAllByOrderByIdAsc();
}
