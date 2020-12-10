package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AppointedDocumentService {

    AppointedDocument save(AppointedDocument appointedDocument);
    AppointedDocument save(JobPosition jobPosition, MultipartFile file);
}
