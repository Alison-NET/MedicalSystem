package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.AppointedDocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.AppointedDocumentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AppointedDocumentServiceImpl implements AppointedDocumentService {

    AppointedDocumentRepo appointedDocumentRepo;

    @Override
    public AppointedDocument save(AppointedDocument intendedDocument) {
        return appointedDocumentRepo.save(intendedDocument);
    }

    @Override
    public AppointedDocument save(JobPosition jobPosition, MultipartFile file) {
        try {
            AppointedDocument document = new AppointedDocument();
            document.setName(file.getOriginalFilename());
            document.setExtension(file.getContentType());
            document.setData(file.getBytes());
            document.setJobPosition(jobPosition);
            return appointedDocumentRepo.save(document);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
