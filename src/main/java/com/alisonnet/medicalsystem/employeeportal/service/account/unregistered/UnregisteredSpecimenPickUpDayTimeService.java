package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnregisteredSpecimenPickUpDayTimeService {
    List<UnregisteredSpecimenPickUpDayTime> findAll();
    int getMaxId();
}
