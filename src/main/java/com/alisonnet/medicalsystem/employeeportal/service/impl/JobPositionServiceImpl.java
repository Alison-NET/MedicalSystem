package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.JobPositionRepo;
import com.alisonnet.medicalsystem.employeeportal.service.JobPositionService;
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
