package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.SpecimenPickUpDayTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpecimenPickUpDayTimeService {
    List<SpecimenPickUpDayTime> findAll();
}
