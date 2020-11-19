package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepo extends JpaRepository<JobPosition, String> {
}