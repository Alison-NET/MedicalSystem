package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.repository.PickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpDayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PickUpDayOfWeekServiceImpl implements PickUpDayOfWeekService {
    PickUpDayOfWeekRepo pickUpDayOfWeekRepo;

    @Override
    public List<PickUpDayOfWeek> findAll() {
        return pickUpDayOfWeekRepo.findAll();
    }

    @Override
    public List<PickUpDayOfWeek> findAllByOrderByIdAsc() {
        return pickUpDayOfWeekRepo.findAllByOrderByIdAsc();
    }
}
