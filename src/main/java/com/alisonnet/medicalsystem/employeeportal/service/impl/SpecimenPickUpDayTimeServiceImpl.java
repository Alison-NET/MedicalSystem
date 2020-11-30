package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.SpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.SpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecimenPickUpDayTimeServiceImpl implements SpecimenPickUpDayTimeService {
    SpecimenPickUpDayTimeRepo specimenPickUpDayTimeRepo;

    @Override
    public List<SpecimenPickUpDayTime> findAll() {
        return specimenPickUpDayTimeRepo.findAll();
    }
}
