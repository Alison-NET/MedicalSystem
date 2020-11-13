package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.repository.CredentialsRepo;
import com.alisonnet.medicalsystem.employeeportal.service.ContractService;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentTypeService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    EmployeeService employeeService;
    DocumentTypeService documentTypeService;
    DocumentService documentService;

    @GetMapping("/profile")
    public String getProfile(Model model){
        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty()){
            return "redirect:/index";
        }

        model.addAttribute("employee", maybeEmployee.get());
        return "employee-profile";
//        return "redirect:/employee-portal/employee/" + maybeEmployee.get().getId();
    }

    @GetMapping("/{id}")
    public String getProfileById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/index";
        }
        log.info(maybeEmployee.get().toString());
        model.addAttribute("employee", maybeEmployee.get());
        return "employee-profile";
    }

    @GetMapping("/{id}/documents")
    public String getDocumentsPage(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        model.addAttribute("employee", maybeEmployee.get());
        model.addAttribute("documentsType", new DocumentType());
        model.addAttribute("documentTypes", documentTypeService.findAll());
        return "employee-documents";
    }

    @PostMapping("/{id}/documents")
    public String uploadDocumentsRequest(@PathVariable int id,
                                         @RequestParam("documentsType") DocumentType documentType,
                                         @RequestParam("files") MultipartFile[] files){
        log.info("Document(s) Type Name: " + documentType.getName());

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }
        Employee employee = maybeEmployee.get();

        log.info("Employee with new docs: " + employee.toString());

        for(MultipartFile file: files){
            documentService.saveFile(file, documentType, employee);
        }

        return "redirect:/employee-portal/employee/" + employee.getId() + "/documents";
    }

}
