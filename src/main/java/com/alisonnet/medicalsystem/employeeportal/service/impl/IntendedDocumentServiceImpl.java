package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.IntendedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.IntendedDocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.IntendedDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class IntendedDocumentServiceImpl implements IntendedDocumentService {

    IntendedDocumentRepo intendedDocumentRepo;

    @Override
    public IntendedDocument save(IntendedDocument intendedDocument) {
        return intendedDocumentRepo.save(intendedDocument);
    }

    @Override
    public IntendedDocument save(JobPosition jobPosition, MultipartFile file) {
        try {
            IntendedDocument document = new IntendedDocument();
            document.setName(file.getOriginalFilename());
            document.setExtension(file.getContentType());
            document.setData(file.getBytes());
            document.setJobPosition(jobPosition);
            return intendedDocumentRepo.save(document);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
