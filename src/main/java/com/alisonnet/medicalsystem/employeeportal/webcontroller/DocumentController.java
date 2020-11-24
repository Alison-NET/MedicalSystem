package com.alisonnet.medicalsystem.employeeportal.webcontroller;


import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id){

        Optional<Document> maybeDocument = documentService.findById(id);
        if(maybeDocument.isEmpty())                                             // Add exception
            return null;

        Document document = maybeDocument.get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getExtension()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + document.getName() + "\"")
                .body(new ByteArrayResource(document.getData()));

    }

    @GetMapping("/{id}/delete")
    public String handleDeletingDocument(@PathVariable int id, HttpServletRequest request){

        documentService.deleteById(id);
        return Optional.ofNullable(request.getHeader("Referer"))
                .map(requestUrl -> "redirect:" + requestUrl)
                .orElse("/");
    }



}
