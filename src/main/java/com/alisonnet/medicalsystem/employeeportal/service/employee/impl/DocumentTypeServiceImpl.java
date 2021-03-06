package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.DocumentTypeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    DocumentTypeRepo documentTypeRepo;

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepo.findAll();
    }

    @Override
    public List<DocumentType> getAllTypesBasedOnEmployee(Employee employee) {
        List<DocumentType> allDocumentTypes = findAll();

        return allDocumentTypes.stream().filter(type ->{
            type.setEmpDocuments(type.getEmpDocuments().stream()
                    .filter(document -> document.getEmployee() == employee)
                    .collect(Collectors.toList()));
            return true;
        }).collect(Collectors.toList());
    }
}
