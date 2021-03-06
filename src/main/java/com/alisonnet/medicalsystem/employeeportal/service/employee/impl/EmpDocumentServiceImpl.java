package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.EmpDocumentRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmpDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpDocumentServiceImpl implements EmpDocumentService {

    EmpDocumentRepo empDocumentRepo;

    EmployeeService employeeService;

    @Override
    public EmpDocument save(MultipartFile file, DocumentType documentType, Employee owner) {
        try{
            EmpDocument document = new EmpDocument();
            document.setName(file.getOriginalFilename());
            document.setExtension(file.getContentType());
            document.setData(file.getBytes());
            document.setDocumentType(documentType);
            document.setEmployee(owner);
            document.setIsLocked(false);
            return empDocumentRepo.save(document);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmpDocument save(EmpDocument document) {
        return empDocumentRepo.save(document);
    }

    @Override
    public Optional<EmpDocument> findById(int id) {
        return empDocumentRepo.findById(id);
    }

    @Override
    public boolean canBeDownloaded(EmpDocument empDocument, Employee employee) {

        if(employeeService.isInAdminDepartment(employee))
            return true;
        else if(employeeService.isInHRDepartment(employee))
            return (!employeeService.isInHRDepartment(empDocument.getEmployee())
                    && !employeeService.isInAdminDepartment(empDocument.getEmployee())) ||
                    employee.getId() == empDocument.getEmployee().getId();
        else
            return employee.getId() == empDocument.getEmployee().getId();
    }

    @Override
    public boolean canBeDeleted(EmpDocument empDocument, Employee employee) {

        if(employeeService.isInAdminDepartment(employee))
            return true;
        else if(employeeService.isInHRDepartment(employee))
            return (!employeeService.isInHRDepartment(empDocument.getEmployee())
                    && !employeeService.isInAdminDepartment(empDocument.getEmployee())) ||
                    (employee.getId() == empDocument.getEmployee().getId() && !empDocument.getIsLocked());
        else
            return employee.getId() == empDocument.getEmployee().getId() && !empDocument.getIsLocked();
    }
}
