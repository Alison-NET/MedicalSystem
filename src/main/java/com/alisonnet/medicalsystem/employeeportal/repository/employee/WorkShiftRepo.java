package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShiftRepo extends JpaRepository<WorkShift, Integer> {
}
