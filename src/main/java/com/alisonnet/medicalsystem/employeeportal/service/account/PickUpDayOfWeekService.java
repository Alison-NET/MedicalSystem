package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpDayOfWeek;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PickUpDayOfWeekService {

    List<PickUpDayOfWeek> findAll();
    List<PickUpDayOfWeek> findAllByOrderByIdAsc();
}
