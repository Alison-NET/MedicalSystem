package com.alisonnet.medicalsystem.employeeportal.dto.document;

import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class JobPosAndFilesDTO {
    private JobPosition jobPosition;
    private MultipartFile[] files;
}
