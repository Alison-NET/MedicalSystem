package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.PickUpTime;
import org.springframework.stereotype.Service;

@Service
public interface PickUpTimeService {

    void remove(PickUpTime pickUpTime);
}
