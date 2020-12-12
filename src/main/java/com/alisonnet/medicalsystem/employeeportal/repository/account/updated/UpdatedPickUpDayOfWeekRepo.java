package com.alisonnet.medicalsystem.employeeportal.repository.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpDayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdatedPickUpDayOfWeekRepo extends JpaRepository<UpdatedPickUpDayOfWeek, Integer> {
    List<UpdatedPickUpDayOfWeek> findAllByOrderByIdAsc();
}
