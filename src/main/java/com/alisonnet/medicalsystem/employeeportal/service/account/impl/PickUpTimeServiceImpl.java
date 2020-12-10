package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.PickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PickUpTimeServiceImpl implements PickUpTimeService {

    private PickUpTimeRepo pickUpTimeRepo;

    @Override
    public void remove(PickUpTime pickUpTime) {
        pickUpTimeRepo.delete(pickUpTime);
    }
}
