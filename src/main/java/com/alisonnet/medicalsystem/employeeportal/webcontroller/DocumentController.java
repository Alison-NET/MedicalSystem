package com.alisonnet.medicalsystem.employeeportal.webcontroller;


import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.AppointedDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmpDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/document")
@AllArgsConstructor
@Slf4j
public class DocumentController {

    DocumentService documentService;

    EmployeeService employeeService;
    AppointedDocumentService appointedDocumentService;
    EmpDocumentService empDocumentService;

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id){
        Employee activeEmployee = employeeService.getActiveEmployee().get();

        Optional<Document> maybeDocument = documentService.findById(id);
        if(maybeDocument.isEmpty())                                             // Add exception
            return null;

        Document document = maybeDocument.get();

        if (document instanceof EmpDocument){
            if (empDocumentService.canBeDownloaded((EmpDocument) document, activeEmployee)){
                log.info("Employee Document can be downloaded");
            }else{
                log.info("Employee Document can't be downloaded");
            }
        } else if (document instanceof AppointedDocument ) {
            if (appointedDocumentService.canBeDownloaded((AppointedDocument) document, activeEmployee)){
                log.info("Appointed Document can be downloaded");
            }else{
                log.info("Appointed Document can't be downloaded");
            }
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getExtension()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }


    @GetMapping("/{id}/delete")
    public String deleteDocument(@PathVariable int id, HttpServletRequest request){
        Employee activeEmployee = employeeService.getActiveEmployee().get();
        Optional<Document> maybeDocument = documentService.findById(id);
        if(maybeDocument.isEmpty())                                             // Add exception
            return null;

        Document document = maybeDocument.get();
        if (document instanceof EmpDocument){
            if (empDocumentService.canBeDeleted((EmpDocument) document, activeEmployee)){
                log.info("Employee Document can be deleted");
            }else{
                log.info("Employee Document can't be deleted");
            }
        } else if (document instanceof AppointedDocument ) {
            if (appointedDocumentService.canBeDeleted((AppointedDocument) document, activeEmployee)){
                log.info("Appointed Document can be deleted");
            }else{
                log.info("Appointed Document can't be deleted");
            }
        }
        documentService.deleteById(id);
        return Optional.ofNullable(request.getHeader("Referer"))
                .map(requestUrl -> "redirect:" + requestUrl)
                .orElse("/");
    }



}
