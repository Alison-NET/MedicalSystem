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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/document")
@AllArgsConstructor
@Slf4j
public class DocumentController {

    DocumentService documentService;

    @GetMapping("/download/{docId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int docId){
        Document document = documentService.findById(docId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getDocExtension()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+document.getDocName()+"\"")
                .body(new ByteArrayResource(document.getData()));

    }
}
