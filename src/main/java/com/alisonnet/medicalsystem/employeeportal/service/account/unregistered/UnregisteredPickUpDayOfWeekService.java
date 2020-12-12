package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredPickUpDayOfWeek;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UnregisteredPickUpDayOfWeekService {
    List<UnregisteredPickUpDayOfWeek> findAll();
    List<UnregisteredPickUpDayOfWeek> findAllByOrderByIdAsc();
}
