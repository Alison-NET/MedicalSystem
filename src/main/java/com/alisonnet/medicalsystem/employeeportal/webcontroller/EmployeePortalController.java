package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.config.EmployeeUserDetails;
import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Department;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.ActiveEmployeeAbsenceException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.InvalidPathVariableException;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DepartmentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.DocumentTypeService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmpDocumentService;
import com.alisonnet.medicalsystem.employeeportal.service.employee.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL)
@Slf4j
public class EmployeePortalController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final DocumentTypeService documentTypeService;
    private final EmpDocumentService empDocumentService;


    @GetMapping
    public String getHomePage(Model model){
        boolean isAttention = false;

        Optional<Employee> activeEmployee = employeeService.getActiveEmployee();
        if(activeEmployee.isPresent()){
            if(employeeService.isInAdminDepartment(activeEmployee.get())
                    || employeeService.isInHRDepartment(activeEmployee.get())){
                //HR ar ADMIN
                List<Department> departmentsWithoutChiefs = departmentService.getDepartmentsWithoutChiefs();
                if(!departmentsWithoutChiefs.isEmpty()) isAttention = true;
                model.addAttribute("departmentsWithoutChief", departmentsWithoutChiefs);
            }
        }
        model.addAttribute("isAttention", isAttention);
        return "employee-portal-home";
    }


    @GetMapping("/employee/profile")
    public String getProfile(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            throw new ActiveEmployeeAbsenceException(Constants.ACTIVE_EMPLOYEE_ABSENCE_MSG);

        return "redirect:/employee-portal/employee/" + maybeEmployee.get().getId();
    }


    @GetMapping("/employee/{id}")
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
            throw new ActiveEmployeeAbsenceException(Constants.ACTIVE_EMPLOYEE_ABSENCE_MSG);

        Employee activeEmp = maybeActiveEmployee.get();
        boolean isMyProfile = activeEmp.getId() == id;

        model.addAttribute("canSeeEditButton", employeeService.canEdit(activeEmp, employee));

        model.addAttribute("canSeeWorkInfo",
                ( isMyProfile
                || employeeService.hasInSubordinates(activeEmp, employee)
                || employeeService.isInHRDepartment(activeEmp) && !employeeService.isInHRDepartment(employee)
                || employeeService.isInAdminDepartment(activeEmp) )
        );

        model.addAttribute("isMyProfile", isMyProfile);

//        model.addAttribute("canManageDocuments", (!employeeService.isInAdminDepartment(activeEmp)) && isMyProfile);

//        model.addAttribute("canManageDocuments",
//                (!(employeeService.isInAdminDepartment(activeEmp) || employeeService.isInHRDepartment(activeEmp))
//                        && isMyProfile)
//                        || (employeeService.isInHRDepartment(activeEmp) && isMyProfile)
//        );

        return "employee-profile";
    }


    @PostMapping("/{id}/upload-documents")
    public String uploadDocumentsRequest(@PathVariable int id,
                                         @ModelAttribute("dto") DocTypeAndFilesDTO dto){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Employee employee = maybeEmployee.get();

        for(MultipartFile file: dto.getFiles())
            empDocumentService.save(file, dto.getDocumentType(), employee);

        return "redirect:/employee-portal/employee/" + employee.getId();
    }


    @GetMapping("/documents-for-job-position")
    public String getDocumentsForJobPosition(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            throw new ActiveEmployeeAbsenceException(Constants.ACTIVE_EMPLOYEE_ABSENCE_MSG);

        model.addAttribute("jobPosition", maybeEmployee.get().getJobPosition());
        return "documents-job-position";
    }

}
