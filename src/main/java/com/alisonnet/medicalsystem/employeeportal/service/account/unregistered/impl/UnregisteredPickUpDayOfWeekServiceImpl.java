package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredPickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpDayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnregisteredPickUpDayOfWeekServiceImpl implements UnregisteredPickUpDayOfWeekService {

    UnregisteredPickUpDayOfWeekRepo unregisteredPickUpDayOfWeekRepo;

    @Override
    public List<UnregisteredPickUpDayOfWeek> findAll() {
        return unregisteredPickUpDayOfWeekRepo.findAll();
    }

    @Override
    public List<UnregisteredPickUpDayOfWeek> findAllByOrderByIdAsc() {
        return unregisteredPickUpDayOfWeekRepo.findAllByOrderByIdAsc();
    }
}
