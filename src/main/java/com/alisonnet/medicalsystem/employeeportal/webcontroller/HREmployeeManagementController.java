package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Contract;
import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.ContractService;
import com.alisonnet.medicalsystem.employeeportal.service.DocumentService;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr/employee")
@AllArgsConstructor
@Slf4j
public class HREmployeeManagementController {

    EmployeeService employeeService;
    DocumentTypeService documentTypeService;
    DocumentService documentService;
    ContractService contractService;

    @GetMapping()
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeProfileById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }
        log.info(maybeEmployee.get().toString());
        model.addAttribute("employee", maybeEmployee.get());
        return "employee-profile";
    }

    @GetMapping("/{id}/new-contract")
    public String createNewContract(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        model.addAttribute("employee", maybeEmployee.get());
        model.addAttribute("contract", new Contract());
        return "contract-create";
    }


    @PostMapping("/{id}/new-contract")
    public String handleContractCreationRequest(@PathVariable int id,
                                                @ModelAttribute Contract contract){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }
        Employee employee = maybeEmployee.get();
        contract.setEmployee(employee);
        contractService.save(contract);

        return "redirect:/employee-portal/hr/employee/" + employee.getId();
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
                                         @ModelAttribute DocumentType documentType,
                                         @RequestParam("files") MultipartFile[] files){
        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        Employee employee = maybeEmployee.get();
        for(MultipartFile file: files){
            documentService.saveFile(file, documentType, employee);
        }

        return "redirect:/employee-portal/hr/employee/" + employee.getId() + "/documents";
    }
}
