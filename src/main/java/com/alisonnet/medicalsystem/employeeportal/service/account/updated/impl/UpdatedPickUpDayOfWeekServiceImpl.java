package com.alisonnet.medicalsystem.employeeportal.service.account.updated.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredPickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.repository.account.updated.UpdatedPickUpDayOfWeekRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedPickUpDayOfWeekService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UpdatedPickUpDayOfWeekServiceImpl implements UpdatedPickUpDayOfWeekService {

    UpdatedPickUpDayOfWeekRepo updatedPickUpDayOfWeekRepo;

    @Override
    public List<UpdatedPickUpDayOfWeek> findAll() {
        return updatedPickUpDayOfWeekRepo.findAll();
    }

    @Override
    public List<UpdatedPickUpDayOfWeek> findAllByOrderByIdAsc() {
        return updatedPickUpDayOfWeekRepo.findAllByOrderByIdAsc();
    }
}
