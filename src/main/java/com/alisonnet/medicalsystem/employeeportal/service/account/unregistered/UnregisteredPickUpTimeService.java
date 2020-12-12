package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpTime;
import org.springframework.stereotype.Service;

@Service
public interface UnregisteredPickUpTimeService {
    void remove(UnregisteredPickUpTime unregisteredPickUpTime);
}
