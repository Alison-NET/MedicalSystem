package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTimeBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.repository.account.PickUpTimeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PickUpTimeServiceImpl implements PickUpTimeService {

    private final PickUpTimeRepo pickUpTimeRepo;

    @Override
    public void remove(PickUpTime pickUpTime) {
        pickUpTimeRepo.delete(pickUpTime);
    }

    @Override
    public int getMaxId() {
        return pickUpTimeRepo.findTopByOrderByIdDesc().map(PickUpTimeBase::getId).orElse(0);
    }
}
