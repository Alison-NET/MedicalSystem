package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.dto.employee.EmployeeIdDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.repository.TitleRepo;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr")
@Slf4j
public class HRController {

    EmployeeService employeeService;
    BasicEmployeeService basicEmployeeService;
    TitleService titleService;
    DepartmentService departmentService;
    RoleService roleService;
    ContractService contractService;
    DocumentTypeService documentTypeService;


    @GetMapping("/approve-employee")
    public String getEmployeesToApprovePage(Model model){

        model.addAttribute("employeesToApprove", basicEmployeeService.getUnapprovedEmployees());
        return "hr-approve-requests";
    }

    @GetMapping("/approve-employee/{id}")
    public String getApprovePage(@PathVariable int id, Model model){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        if(maybeBasicEmployee.isEmpty())
            return "redirect:/employee-portal/hr/approve-employee";

        Employee newEmployee = new Employee();

        newEmployee.setBasicInfo(maybeBasicEmployee.get());

        WorkShift workShift = new WorkShift();
        newEmployee.setWorkShift(workShift);

        Credentials credentials = new Credentials();
        List<Role> roles = new ArrayList<>();
        credentials.setRoles(roles);
        newEmployee.setCredentials(credentials);

        List<Document> documents = new ArrayList<>();
        newEmployee.setDocuments(documents);

        List<Employee> subordinates = new ArrayList<>();
        newEmployee.setSubordinates(subordinates);

        List<Contract> contracts = new ArrayList<>();
        newEmployee.setContracts(contracts);


        model.addAttribute("employee", newEmployee);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "hr-approve-edit-employee";
    }


    @GetMapping("/approve-employee/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        if(maybeBasicEmployee.isEmpty())                                                    //Add exception
            return "redirect:/employee-portal/hr/approve-employee";

        basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
    }


    @PostMapping("/employee/save")
    public String handleSavingEmployee(@Valid @ModelAttribute Employee employee,
                                       BindingResult bindingResult,
                                       HttpServletRequest request,
                                       Model model){

        log.info(bindingResult.toString());                                 // ??????????????????????
        if(bindingResult.hasErrors()){
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");
        }

        employeeService.updateRelationsIfNeeded(employee);

        employee.getBasicInfo().setFullInfo(employee);
        basicEmployeeService.save(employee.getBasicInfo());
        return "redirect:/employee-portal/hr/employee";
    }


    @GetMapping("/employee")
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "hr-employees";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeEditProfilePageById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        model.addAttribute("employee", maybeEmployee.get());
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("isApproved", true);

        //        Contracts management
        model.addAttribute("contract", new Contract());

        //        Documents management
        model.addAttribute("documentTypes", documentTypeService.getAllTypesBasedOnEmployee(maybeEmployee.get()));
        model.addAttribute("dto", new DocTypeAndFilesDTO(new DocumentType(), new MultipartFile[10]));

        //        Supervisor adding
        model.addAttribute("supervisorId", new EmployeeIdDTO());
        model.addAttribute("supervisors", employeeService.getPossibleSupervisors(id));

        return "hr-approve-edit-employee";
    }


    @PostMapping("/employee/{id}/new-contract")
    public String handleContractCreationRequest(@PathVariable int id,
                                                @ModelAttribute Contract contract){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        Employee employee = maybeEmployee.get();
        contract.setEmployee(employee);
        contractService.save(contract);


        return "redirect:/employee-portal/hr/employee/" + id;
    }

    @PostMapping("/employee/{id}/add-supervisor")
    public String handleSubordinateAddingRequest(@PathVariable int id,
                                                @ModelAttribute EmployeeIdDTO supervisorId){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        Optional<Employee> maybeSupervisor = employeeService.findById(supervisorId.getId());
        if(maybeSupervisor.isEmpty()){
            return "redirect:/employee-portal/hr/employee";                 // Add exception
        }

        maybeEmployee.get().setSupervisor(maybeSupervisor.get());

        employeeService.save(maybeEmployee.get());

        return "redirect:/employee-portal/hr/employee/" + id;
    }



}
