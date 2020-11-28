package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.EmpDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentTypeService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    EmployeeService employeeService;
    DocumentTypeService documentTypeService;
    EmpDocumentService empDocumentService;

    @GetMapping("/profile")
    public String getProfile(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        model.addAttribute("employee", maybeEmployee.get());
        return "redirect:/employee-portal/employee/" + maybeEmployee.get().getId();
    }

    @GetMapping("/documents-for-job-position")
    public String getDocumentsForJobPosition(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        model.addAttribute("jobPosition", maybeEmployee.get().getJobPosition());
        return "employee-documents-job-position";
    }

    @GetMapping("/{id}")
    public String getProfilePageById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        Employee employee = maybeEmployee.get();

        model.addAttribute("employee", employee);
        model.addAttribute("documentTypes", documentTypeService.getAllTypesBasedOnEmployee(employee));
        model.addAttribute("dto", new DocTypeAndFilesDTO(new DocumentType(), new MultipartFile[10]));
        return "employee-profile";
    }

    @PostMapping("/{id}/upload-documents")
    public String uploadDocumentsRequest(@PathVariable int id,
                                         @ModelAttribute("dto") DocTypeAndFilesDTO dto){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            return "redirect:/employee-portal/hr/employee";

        Employee employee = maybeEmployee.get();

        for(MultipartFile file: dto.getFiles())
            empDocumentService.save(file, dto.getDocumentType(), employee);

        return "redirect:/employee-portal/employee/" + employee.getId();
    }

}
