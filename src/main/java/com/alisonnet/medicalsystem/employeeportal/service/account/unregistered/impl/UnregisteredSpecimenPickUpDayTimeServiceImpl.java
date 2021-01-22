package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.SpecimenPickUpDayTimeBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredSpecimenPickUpDayTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredSpecimenPickUpDayTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnregisteredSpecimenPickUpDayTimeServiceImpl implements UnregisteredSpecimenPickUpDayTimeService {

    UnregisteredSpecimenPickUpDayTimeRepo unregisteredSpecimenPickUpDayTimeRepo;

    @Override
    public List<UnregisteredSpecimenPickUpDayTime> findAll() {
        return unregisteredSpecimenPickUpDayTimeRepo.findAll();
    }

    @Override
    public int getMaxId() {
        return  unregisteredSpecimenPickUpDayTimeRepo.findTopByOrderByIdDesc().map(SpecimenPickUpDayTimeBase::getId).orElse(0);
    }
}
