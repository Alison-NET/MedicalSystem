package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.SpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.SpecimenPickUpDayTimeService;
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
