package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.PickUpDayOfWeek;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PickUpDayOfWeekService {

    List<PickUpDayOfWeek> findAll();
    List<PickUpDayOfWeek> findAllByOrderByIdAsc();
}
