package com.alisonnet.medicalsystem.employeeportal.service.account.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.repository.account.PickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpDayOfWeekService;
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
