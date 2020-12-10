package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Document;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.DocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    DocumentRepo documentRepo;

    @Override
    public Optional<Document> findById(int id) {
        return documentRepo.findById(id);
    }

    @Override
    public void deleteById(int id) {
        documentRepo.deleteById(id);
    }
}
