package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpTime;
import org.springframework.stereotype.Service;

@Service
public interface UpdatedPickUpTimeService {
    void remove(UpdatedPickUpTime updatedPickUpTime);
}
