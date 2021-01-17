package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.dto.document.JobPosAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.dto.employee.EmployeeIdDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.*;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.AccessDeniedException;
import com.alisonnet.medicalsystem.employeeportal.exception.exceptions.InvalidPathVariableException;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import com.alisonnet.medicalsystem.employeeportal.service.employee.*;
import com.alisonnet.medicalsystem.employeeportal.validator.BasicEmployeePersonalEmailValidator;
import com.alisonnet.medicalsystem.employeeportal.validator.CredentialsEmailValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr")
@Slf4j
public class HREmployeeController {

    EmployeeService employeeService;
    BasicEmployeeService basicEmployeeService;
    TitleService titleService;
    DepartmentService departmentService;
    JobPositionService jobPositionService;
    ContractService contractService;

    DocumentTypeService documentTypeService;
    EmpDocumentService empDocumentService;
    AppointedDocumentService appointedDocumentService;

    BasicEmployeePersonalEmailValidator basicEmployeePersonalEmailValidator;
    CredentialsEmailValidator credentialsEmailValidator;

    @GetMapping("/approve-employee")
    public String getEmployeesToApprovePage(Model model){
        model.addAttribute("employeesToApprove", basicEmployeeService.getUnapprovedEmployees());
        return "hr/approve-requests";
    }

    @GetMapping("/approve-employee/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){

        if(basicEmployeeService.getUnapprovedEmployees().stream()
                .anyMatch(basicEmployee -> basicEmployee == basicEmployeeService.findById(id).orElse(null)))
            basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
    }


    @GetMapping("/approve-employee/{id}")
    public String getApprovePage(@PathVariable int id, Model model){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        if(maybeBasicEmployee.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        if(maybeBasicEmployee.get().getFullInfo() != null )
            throw new InvalidPathVariableException(Constants.ALREADY_REGISTERED_EMPLOYEE_MSG);

        Employee newEmployee = new Employee();

        newEmployee.setBasicInfo(maybeBasicEmployee.get());

        WorkShift workShift = new WorkShift();
        newEmployee.setWorkShift(workShift);

        Credentials credentials = new Credentials();
        newEmployee.setCredentials(credentials);

        List<EmpDocument> documents = new ArrayList<>();
        newEmployee.setEmpDocuments(documents);

        List<Employee> subordinates = new ArrayList<>();
        newEmployee.setSubordinates(subordinates);

        List<Contract> contracts = new ArrayList<>();
        newEmployee.setContracts(contracts);


        model.addAttribute("employee", newEmployee);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("departments", departmentService.findAllByOrderByNameAsc());
        return "hr/approve-edit-employee";
    }




    @PostMapping("/employee/save")
    public String handleSavingEmployee(@Valid @ModelAttribute Employee employee,
                                       BindingResult bindingResult,
                                       Model model){

        basicEmployeePersonalEmailValidator.setFieldPath("basicInfo.personalEmail");
        basicEmployeePersonalEmailValidator.validate(employee.getBasicInfo(), bindingResult);

        credentialsEmailValidator.setFieldPath("credentials.email");
        credentialsEmailValidator.validate(employee.getCredentials(), bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAllByOrderByNameAsc());
            model.addAttribute("titles", titleService.findAllByOrderByIdAsc());

            if(employeeService.findById(employee.getId()).isPresent())
                setupEditEmployeeAttributes(model, employee);

            return "hr/approve-edit-employee";
        }

        employeeService.updateRelationsIfNeeded(employee);

        employee.getBasicInfo().setFullInfo(employee);
        basicEmployeeService.save(employee.getBasicInfo());
        return "redirect:/employee-portal/hr/employee";
    }


    @GetMapping("/employee")
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "hr/employees";
    }


    @GetMapping("/employee/{id}")
    public String getEmployeeEditProfilePageById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Employee empToEdit = maybeEmployee.get();

        setupEditEmployeeAttributes(model, empToEdit);

        return "hr/approve-edit-employee";
    }

    private void setupEditEmployeeAttributes(Model model, Employee employeeToEdit) {
        Employee activeEmployee = employeeService.getActiveEmployee().get();

        if (!employeeService.canBeEdited(employeeToEdit, activeEmployee))
            throw new AccessDeniedException(Constants.VIEW_PAGE_ACCESS_DENIED_MSG);


        //
        model.addAttribute("employee", employeeToEdit);
        model.addAttribute("departments", departmentService.findAllByOrderByNameAsc());
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("isApproved", true);

        //        Contracts management
        model.addAttribute("contract", new Contract());

        //        Documents management
        model.addAttribute("documentTypes", documentTypeService.getAllTypesBasedOnEmployee(employeeToEdit));
        model.addAttribute("dto", new DocTypeAndFilesDTO(new DocumentType(), new MultipartFile[10]));

        //        Supervisor adding
        model.addAttribute("supervisorId", new EmployeeIdDTO());
        model.addAttribute("supervisors", employeeService.getPossibleSupervisorsFor(employeeToEdit));
    }


    @PostMapping("/employee/{id}/new-contract")
    public String handleContractCreationRequest(@PathVariable int id,
                                                @ModelAttribute @Valid Contract contract,
                                                BindingResult bindingResult,
                                                Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Employee employee = maybeEmployee.get();
        if(bindingResult.hasErrors()) {
            setupEditEmployeeAttributes(model, employee);
            model.addAttribute("contract", contract);
            return "hr/approve-edit-employee";
        }

        contract.setEmployee(employee);
        contractService.save(contract);

        return "redirect:/employee-portal/hr/employee/" + id;
    }


    @PostMapping("/employee/{id}/add-supervisor")
    public String handleSubordinateAddingRequest(@PathVariable int id,
                                                @ModelAttribute EmployeeIdDTO supervisorId){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Optional<Employee> maybeSupervisor = employeeService.findById(supervisorId.getId());
        if(maybeSupervisor.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);


        maybeEmployee.get().setSupervisor(maybeSupervisor.get());

        employeeService.save(maybeEmployee.get());

        return "redirect:/employee-portal/hr/employee/" + id;
    }


    @GetMapping("/employee/{id}/document/{docId}/lock")
    public String lockEmpDocument(@PathVariable int id, @PathVariable int docId, HttpServletRequest request){

        if(employeeService.findById(id).isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Optional<EmpDocument> maybeDocument = empDocumentService.findById(docId);
        if(maybeDocument.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_DOCUMENT_ID_MSG);

        EmpDocument document = maybeDocument.get();
        document.setIsLocked(true);
        empDocumentService.save(document);

        return Optional.ofNullable(request.getHeader("Referer"))
                .map(requestUrl -> "redirect:" + requestUrl)
                .orElse("/");
    }


    @GetMapping("/employee/{id}/document/{docId}/unlock")
    public String unlockEmpDocument(@PathVariable int id, @PathVariable int docId, HttpServletRequest request){

        if(employeeService.findById(id).isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_EMPLOYEE_ID_MSG);

        Optional<EmpDocument> maybeDocument = empDocumentService.findById(docId);
        if(maybeDocument.isEmpty())
            throw new InvalidPathVariableException(Constants.INVALID_DOCUMENT_ID_MSG);

        EmpDocument document = maybeDocument.get();
        document.setIsLocked(false);
        empDocumentService.save(document);

        return Optional.ofNullable(request.getHeader("Referer"))
                .map(requestUrl -> "redirect:" + requestUrl)
                .orElse("/");
    }


    @GetMapping("/documents-for-job-position")
    public String getManageDocumentsPerJobPositionPage(Model model){
        model.addAttribute("jobPositions", jobPositionService.findAll());
        model.addAttribute("jobPosAndFilesDTO", new JobPosAndFilesDTO(new JobPosition(), new MultipartFile[10]));
        return "hr/documents-job-position";
    }


    @PostMapping("/documents-for-job-position/save")
    public String handleAddingIntendedDocument(@ModelAttribute("jobPosAndFilesDTO") JobPosAndFilesDTO dto){
        for(MultipartFile file: dto.getFiles())
            appointedDocumentService.save(dto.getJobPosition(), file);

        return "redirect:/employee-portal/hr/documents-for-job-position";
    }

}
