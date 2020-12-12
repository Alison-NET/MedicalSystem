package com.alisonnet.medicalsystem.employeeportal.service.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedPickUpDayOfWeek;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UpdatedPickUpDayOfWeekService {
    List<UpdatedPickUpDayOfWeek> findAll();
    List<UpdatedPickUpDayOfWeek> findAllByOrderByIdAsc();
}
