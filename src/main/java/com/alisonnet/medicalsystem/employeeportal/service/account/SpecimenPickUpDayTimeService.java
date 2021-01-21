package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecimenPickUpDayTimeService {
    List<SpecimenPickUpDayTime> findAll();
    int getMaxId();
}
