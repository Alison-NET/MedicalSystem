package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTime;
import org.springframework.stereotype.Service;

@Service
public interface PickUpTimeService {

    void remove(PickUpTime pickUpTime);
}
