package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredPickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedPickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpTimeService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedPickUpTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdatedPickUpTimeServiceImpl implements UpdatedPickUpTimeService {

    UpdatedPickUpTimeRepo updatedPickUpTimeRepo;

    @Override
    public void remove(UpdatedPickUpTime updatedPickUpTime) {
        updatedPickUpTimeRepo.delete(updatedPickUpTime);
    }
}
