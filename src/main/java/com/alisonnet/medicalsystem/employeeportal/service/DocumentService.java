package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DocumentService {

    Optional<Document> findById(int id);
    void deleteById(int id);
}
