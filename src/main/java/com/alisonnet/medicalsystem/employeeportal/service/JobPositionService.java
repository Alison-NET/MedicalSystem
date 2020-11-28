package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobPositionService {

    List<JobPosition> findAll();
}
