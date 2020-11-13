package com.alisonnet.medicalsystem.employeeportal.dto.document;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DocTypeAndFilesListDTO {
    private List<DocTypeAndFilesDTO> docTypeAndFilesDTOList;
}
