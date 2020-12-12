package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedSpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedSpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdatedSpecimenPickUpDayTimeServiceImpl implements UpdatedSpecimenPickUpDayTimeService {

    UpdatedSpecimenPickUpDayTimeRepo updatedSpecimenPickUpDayTimeRepo;

    @Override
    public List<UpdatedSpecimenPickUpDayTime> findAll() {
        return updatedSpecimenPickUpDayTimeRepo.findAll();
    }
}
