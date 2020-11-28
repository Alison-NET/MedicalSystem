package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.IntendedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IntendedDocumentService {

    IntendedDocument save(IntendedDocument intendedDocument);
    IntendedDocument save(JobPosition jobPosition, MultipartFile file);
}
