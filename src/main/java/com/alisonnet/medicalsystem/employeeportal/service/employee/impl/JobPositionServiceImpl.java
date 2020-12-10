package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.JobPositionRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.JobPositionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobPositionServiceImpl implements JobPositionService {

    JobPositionRepo jobPositionRepo;

    @Override
    public List<JobPosition> findAll() {
        return jobPositionRepo.findAll();
    }
}
