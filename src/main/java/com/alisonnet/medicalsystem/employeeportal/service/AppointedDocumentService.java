package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AppointedDocumentService {

    AppointedDocument save(AppointedDocument appointedDocument);
    AppointedDocument save(JobPosition jobPosition, MultipartFile file);
}
