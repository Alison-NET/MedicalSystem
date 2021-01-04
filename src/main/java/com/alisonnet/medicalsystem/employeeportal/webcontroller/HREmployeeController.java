package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.document.DocTypeAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.dto.document.JobPosAndFilesDTO;
import com.alisonnet.medicalsystem.employeeportal.dto.employee.EmployeeIdDTO;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.*;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import com.alisonnet.medicalsystem.employeeportal.service.employee.*;
import com.alisonnet.medicalsystem.employeeportal.validator.BasicEmployeePersonalEmailValidator;
import com.alisonnet.medicalsystem.employeeportal.validator.CredentialsEmailValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @GetMapping("/approve-employee/{id}")
    public String getApprovePage(@PathVariable int id, Model model){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        if(maybeBasicEmployee.isEmpty())
            return "redirect:/employee-portal/hr/approve-employee";

        if(maybeBasicEmployee.get().getFullInfo() != null )                 // Add exception
            return "redirect:/employee-portal/hr/approve-employee";

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


        model.addAttribute("canEdit",true);

        model.addAttribute("employee", newEmployee);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("departments", departmentService.findAllByOrderByNameAsc());
        return "hr/approve-edit-employee";
    }


    @GetMapping("/approve-employee/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        if(maybeBasicEmployee.isEmpty())                                                    //Add exception
            return "redirect:/employee-portal/hr/approve-employee";

        if(maybeBasicEmployee.get().getFullInfo() != null)                                  //Add exception
            return "redirect:/employee-portal/hr/approve-employee";

        basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
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
            model.addAttribute("canEdit",true );

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
            return "redirect:/employee-portal/hr/employee";

        Employee empToEdit = maybeEmployee.get();

        setupEditEmployeeAttributes(model, empToEdit);

        return "hr/approve-edit-employee";
    }

    private void setupEditEmployeeAttributes(Model model, Employee empToEdit) {
        Employee activeEmp = employeeService.getActiveEmployee().get();
        model.addAttribute("canEdit",
                !((employeeService.isInHRDepartment(activeEmp) && employeeService.isInHRDepartment(empToEdit))
                || (employeeService.isInHRDepartment(activeEmp) && employeeService.isInAdminDepartment(empToEdit))));

        //
        model.addAttribute("employee", empToEdit);
        model.addAttribute("departments", departmentService.findAllByOrderByNameAsc());
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("isApproved", true);

        //        Contracts management
        model.addAttribute("contract", new Contract());

        //        Documents management
        model.addAttribute("documentTypes", documentTypeService.getAllTypesBasedOnEmployee(empToEdit));
        model.addAttribute("dto", new DocTypeAndFilesDTO(new DocumentType(), new MultipartFile[10]));

        //        Supervisor adding
        model.addAttribute("supervisorId", new EmployeeIdDTO());
        model.addAttribute("supervisors", employeeService.getPossibleSupervisors(empToEdit.getId()));
    }


    @PostMapping("/employee/{id}/new-contract")
    public String handleContractCreationRequest(@PathVariable int id,
                                                @ModelAttribute @Valid Contract contract,
                                                BindingResult bindingResult,
                                                Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(maybeEmployee.isEmpty())
            return "redirect:/employee-portal/hr/employee";

        if(bindingResult.hasErrors()) {
            setupEditEmployeeAttributes(model, maybeEmployee.get());
            model.addAttribute("contract", contract);
            return "hr/approve-edit-employee";
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
        if(maybeEmployee.isEmpty())
            return "redirect:/employee-portal/hr/employee";

        Optional<Employee> maybeSupervisor = employeeService.findById(supervisorId.getId());
        if(maybeSupervisor.isEmpty())
            return "redirect:/employee-portal/hr/employee";                 // Add exception

        maybeEmployee.get().setSupervisor(maybeSupervisor.get());

        employeeService.save(maybeEmployee.get());

        return "redirect:/employee-portal/hr/employee/" + id;
    }


    @GetMapping("/employee/{id}/document/{docId}/lock")
    public String lockEmpDocument(@PathVariable int id, @PathVariable int docId){

        if(employeeService.findById(id).isEmpty())
            return "redirect:/employee-portal/hr/employee";

        Optional<EmpDocument> maybeDocument = empDocumentService.findById(docId);
        if(maybeDocument.isEmpty())                                             // Add exception
            return "redirect:/employee-portal/hr/employee/" + id;

        EmpDocument document = maybeDocument.get();
        document.setIsLocked(true);
        empDocumentService.save(document);

        return "redirect:/employee-portal/hr/employee/" + id;
    }


    @GetMapping("/employee/{id}/document/{docId}/unlock")
    public String unlockEmpDocument(@PathVariable int id, @PathVariable int docId){

        if(employeeService.findById(id).isEmpty())
            return "redirect:/employee-portal/hr/employee";

        Optional<EmpDocument> maybeDocument = empDocumentService.findById(docId);
        if(maybeDocument.isEmpty())                                             // Add exception
            return "redirect:/employee-portal/hr/employee/" + id;

        EmpDocument document = maybeDocument.get();
        document.setIsLocked(false);
        empDocumentService.save(document);

        return "redirect:/employee-portal/hr/employee/" + id;
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
