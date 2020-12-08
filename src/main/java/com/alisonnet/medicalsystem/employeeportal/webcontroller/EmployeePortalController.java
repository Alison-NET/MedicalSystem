package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.Department;
import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.entity.JobPosition;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentTypeService;
import com.alisonnet.medicalsystem.employeeportal.service.EmpDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;


@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL)
@Slf4j
public class EmployeePortalController {

    DepartmentService departmentService;
    EmployeeService employeeService;
    DocumentTypeService documentTypeService;
    EmpDocumentService empDocumentService;

    @GetMapping
    public String getHomePage(Model model){
        //HR
        model.addAttribute("departmentsWithoutChief", departmentService.getDepartmentsWithoutChiefs());
        return "employee-portal-home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        return "redirect:/employee-portal/" + maybeEmployee.get().getId();
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

        // authorities
        Optional<Employee> maybeActiveEmployee = employeeService.getActiveEmployee();
        if(maybeActiveEmployee.isEmpty())
            return "redirect:/index";

        model.addAttribute("isMyProfile", maybeActiveEmployee.get().getId() == id);
        model.addAttribute("editButtonShow",
                ( !(employeeService.isInHRDepartment(employee) || employeeService.isInAdminDepartment(employee))
                        && employeeService.isInHRDepartment(maybeActiveEmployee.get()))
                        || employeeService.isInAdminDepartment(maybeActiveEmployee.get())
        );

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

        return "redirect:/employee-portal/" + employee.getId();
    }


    @GetMapping("/documents-for-job-position")
    public String getDocumentsForJobPosition(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        model.addAttribute("jobPosition", maybeEmployee.get().getJobPosition());
        return "documents-job-position";
    }

}
