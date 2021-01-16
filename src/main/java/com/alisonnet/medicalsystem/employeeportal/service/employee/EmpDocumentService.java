package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
public interface EmpDocumentService {

    EmpDocument save(EmpDocument document);
    EmpDocument save(MultipartFile file, DocumentType documentType, Employee owner);
    Optional<EmpDocument> findById(int id);
    boolean canBeDownloaded(EmpDocument empDocument, Employee employee);
    boolean canBeDeleted(EmpDocument empDocument, Employee employee);
}
