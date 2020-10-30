package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JopPositionRepo extends JpaRepository<JobPosition, String> {
}
