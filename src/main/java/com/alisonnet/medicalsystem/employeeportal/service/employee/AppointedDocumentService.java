package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface AppointedDocumentService {

    AppointedDocument save(AppointedDocument appointedDocument);
    AppointedDocument save(JobPosition jobPosition, MultipartFile file);
    Optional<AppointedDocument> findById(int id);
    boolean canBeDownloaded(AppointedDocument empDocument, Employee employee);
    boolean canBeDeleted(AppointedDocument empDocument, Employee employee);
}
