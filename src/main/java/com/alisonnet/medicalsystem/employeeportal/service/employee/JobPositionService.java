package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobPositionService {

    List<JobPosition> findAll();
}
