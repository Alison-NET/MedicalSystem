package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentTypeService {
    List<DocumentType> findAll();
}
