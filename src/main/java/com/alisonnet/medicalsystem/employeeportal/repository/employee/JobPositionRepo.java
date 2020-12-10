package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepo extends JpaRepository<JobPosition, Integer> {
}
