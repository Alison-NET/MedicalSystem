package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.SpecimenPickUpDayTimeBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.SpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.SpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SpecimenPickUpDayTimeServiceImpl implements SpecimenPickUpDayTimeService {

    private final SpecimenPickUpDayTimeRepo specimenPickUpDayTimeRepo;

    @Override
    public List<SpecimenPickUpDayTime> findAll() {
        return specimenPickUpDayTimeRepo.findAll();
    }

    @Override
    public int getMaxId() {
        return specimenPickUpDayTimeRepo.findTopByOrderByIdDesc().map(SpecimenPickUpDayTimeBase::getId).orElse(0);
    }
}
