package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTimeBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredPickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnregisteredPickUpTimeServiceImpl implements UnregisteredPickUpTimeService {

    UnregisteredPickUpTimeRepo unregisteredPickUpTimeRepo;

    @Override
    public void remove(UnregisteredPickUpTime unregisteredPickUpTime) {
        unregisteredPickUpTimeRepo.delete(unregisteredPickUpTime);
    }

    @Override
    public int getMaxId() {
        return unregisteredPickUpTimeRepo.findTopByOrderByIdDesc().map(PickUpTimeBase::getId).orElse(0);
    }
}
