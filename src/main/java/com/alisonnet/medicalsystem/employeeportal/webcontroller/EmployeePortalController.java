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

        List<Department> departmentsWithoutChiefs = departmentService.getDepartmentsWithoutChiefs();

        log.info(departmentsWithoutChiefs.toString());
        //HR
        model.addAttribute("departmentsWithoutChief", departmentsWithoutChiefs );

        return "employee-portal-home";
    }

    @GetMapping("/profile")
    public String getProfile(Model model){

        Optional<Employee> maybeEmployee = employeeService.getActiveEmployee();
        if(maybeEmployee.isEmpty())
            return "redirect:/index";

        model.addAttribute("employee", maybeEmployee.get());
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

        log.info(employeeService.isInHRDepartment(employee)+"");
        log.info(employeeService.isInAdminDepartment(employee)+"");

        model.addAttribute("isHrProfile", employeeService.isInHRDepartment(employee));
        model.addAttribute("isAdminProfile", employeeService.isInAdminDepartment(employee));
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

    @GetMapping("/test/add-department")
    public String testAddDep(Model model){

        Department department = new Department();

        Random random = new Random();
        List<JobPosition> jobPositions = new ArrayList<>();
        for(int i = 0; i< 3; i++){

            JobPosition jobPosition = new JobPosition();
            jobPosition.setId(random.nextInt());
            jobPosition.setName(random.nextDouble()+"");

            jobPositions.add(jobPosition);
        }

        department.setJobPositions(jobPositions);
        model.addAttribute("department", department);
        return "test-add-department";
    }

    @PostMapping("/test/add-department")
    public String handleAddDep(@Valid @ModelAttribute Department department){
        log.info(department.getJobPositions().get(0).toString());
        return "test-add-department";
    }

}
