package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.repository.DocumentTypeRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    DocumentTypeRepo documentTypeRepo;

    @Override
    public List<DocumentType> findAll() {
        return documentTypeRepo.findAll();
    }
}
