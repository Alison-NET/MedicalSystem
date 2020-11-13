package com.alisonnet.medicalsystem.employeeportal.dto.document;

import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class DocTypeAndFilesDTO {

    private DocumentType documentType;
    private MultipartFile[] files;
}
