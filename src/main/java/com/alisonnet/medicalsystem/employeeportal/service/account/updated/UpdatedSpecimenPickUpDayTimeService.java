package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UpdatedSpecimenPickUpDayTimeService {
    List<UpdatedSpecimenPickUpDayTime> findAll();
}
