package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import com.alisonnet.medicalsystem.employeeportal.repository.TitleRepo;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TitleServiceImpl implements TitleService {

    private TitleRepo titleRepo;

    @Override
    public List<Title> findAll() {
        return titleRepo.findAll();
    }
}
