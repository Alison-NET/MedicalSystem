package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DocumentTypeService {

    List<DocumentType> findAll();
    List<DocumentType> getAllTypesBasedOnEmployee(Employee employee);
}
