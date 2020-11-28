package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Service
public interface EmpDocumentService {

    EmpDocument save(EmpDocument document);
    EmpDocument save(MultipartFile file, DocumentType documentType, Employee owner);
    Optional<EmpDocument> findById(int id);

}
