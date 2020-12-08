package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.repository.PickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpTimeService;
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
