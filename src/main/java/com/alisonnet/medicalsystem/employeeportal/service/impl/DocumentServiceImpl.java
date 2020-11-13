package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.DocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    DocumentRepo documentRepo;

    @Override
    public Document saveFile(MultipartFile file, DocumentType documentType, Employee owner) {
        String docName = file.getOriginalFilename();
        try{
            Document document = new Document();
            document.setDocName(docName);
            document.setDocExtension(file.getContentType());
            document.setData(file.getBytes());
            document.setDocumentType(documentType);
            document.setEmployee(owner);
            return documentRepo.save(document);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Document save(Document document) {
        return documentRepo.save(document);
    }

    @Override
    public Optional<Document> findById(int id) {
        return documentRepo.findById(id);
    }

    @Override
    public Optional<List<Document>> findByEmployeeAndDocType(Employee employee, DocumentType documentType) {
        return documentRepo.findDocumentsByEmployeeAndDocumentType(employee, documentType);
    }
}
