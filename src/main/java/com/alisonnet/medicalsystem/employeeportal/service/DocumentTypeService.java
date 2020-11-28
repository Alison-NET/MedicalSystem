package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentTypeService {

    List<DocumentType> findAll();
    List<DocumentType> getAllTypesBasedOnEmployee(Employee employee);
}
