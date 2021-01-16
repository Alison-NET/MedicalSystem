package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.AppointedDocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.AppointedDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppointedDocumentServiceImpl implements AppointedDocumentService {

    AppointedDocumentRepo appointedDocumentRepo;
    EmployeeService employeeService;

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

    @Override
    public Optional<AppointedDocument> findById(int id) {
        return appointedDocumentRepo.findById(id);
    }


    @Override
    public boolean canBeDownloaded(AppointedDocument empDocument, Employee employee) {
        if(employeeService.isInHRDepartment(employee) || employeeService.isInAdminDepartment(employee))
            return true;
        else
            return employee.getJobPosition() == empDocument.getJobPosition();
    }

    @Override
    public boolean canBeDeleted(AppointedDocument empDocument, Employee employee) {
        return employeeService.isInHRDepartment(employee) || employeeService.isInAdminDepartment(employee);
    }
}
