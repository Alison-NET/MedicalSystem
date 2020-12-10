package com.alisonnet.medicalsystem.employeeportal.repository.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickUpDayOfWeekRepo extends JpaRepository<PickUpDayOfWeek, Integer> {
    List<PickUpDayOfWeek> findAllByOrderByIdAsc();
}
