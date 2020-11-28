package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TitleService {

    List<Title> findAll();
}
