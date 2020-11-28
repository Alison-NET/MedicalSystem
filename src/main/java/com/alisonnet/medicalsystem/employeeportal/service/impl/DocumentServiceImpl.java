package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.repository.DocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentService;
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
