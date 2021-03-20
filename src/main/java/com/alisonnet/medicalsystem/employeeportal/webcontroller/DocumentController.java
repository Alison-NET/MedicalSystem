package com.alisonnet.medicalsystem.employeeportal.webcontroller;


import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.AccessDeniedException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.InvalidPathVariableException;
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

    private final DocumentService documentService;

    private final EmployeeService employeeService;
    private final AppointedDocumentService appointedDocumentService;
    private final EmpDocumentService empDocumentService;

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id){
        Employee activeEmployee = employeeService.getActiveEmployee().get();

        Optional<Document> maybeDocument = documentService.findById(id);
        if(maybeDocument.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_DOCUMENT_ID_MSG);

        Document document = maybeDocument.get();

        if (document instanceof EmpDocument){
            if (!empDocumentService.canBeDownloaded((EmpDocument) document, activeEmployee))
                throw new AccessDeniedException(Constants.DOWNLOAD_DOCUMENT_ACCESS_DENIED_MSG);
        } else if (document instanceof AppointedDocument ) {
            if (!appointedDocumentService.canBeDownloaded((AppointedDocument) document, activeEmployee))
                throw new AccessDeniedException(Constants.DOWNLOAD_DOCUMENT_ACCESS_DENIED_MSG + " appointed");
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
        if(maybeDocument.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_DOCUMENT_ID_MSG);

        Document document = maybeDocument.get();
        if (document instanceof EmpDocument){
            if (!empDocumentService.canBeDeleted((EmpDocument) document, activeEmployee))
                throw new AccessDeniedException(Constants.DELETE_DOCUMENT_ACCESS_DENIED_MSG);
        } else if (document instanceof AppointedDocument ) {
            if (!appointedDocumentService.canBeDeleted((AppointedDocument) document, activeEmployee))
                throw new AccessDeniedException(Constants.DELETE_DOCUMENT_ACCESS_DENIED_MSG + " appointed");
        }
        documentService.deleteById(id);
        return Optional.ofNullable(request.getHeader("Referer"))
                .map(requestUrl -> "redirect:" + requestUrl)
                .orElse("/");
    }



}
