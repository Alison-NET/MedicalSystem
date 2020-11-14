package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr")
@Slf4j
public class HRController {

    EmployeeService employeeService;
    BasicEmployeeService basicEmployeeService;
    DepartmentService departmentService;
    RoleService roleService;
    ContractService contractService;

    @GetMapping("/approve-employee")
    public String getEmployeesToApprovePage(Model model){
        // basic employees info & employees to approve
        List<BasicEmployee> basicEmployees = basicEmployeeService.findAll();

        //all approved employees
        List<Employee> employees = employeeService.findAll();

        // receive non approved employees
        List<BasicEmployee> nonApprovedEmployees =
                basicEmployees.stream()
                        .filter(basicEmployee -> employees.stream()
                                .noneMatch(employee -> employee.getBasicInfo().equals(basicEmployee))
                        ).collect(Collectors.toList());

        model.addAttribute("employeesToApprove", nonApprovedEmployees);
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
        model.addAttribute("departments", departmentService.findAll());
        return "hr-approve-edit-employee";
    }


    @GetMapping("/approve-employee/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){
        basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
    }


    @PostMapping("/employee/save")
    public String handleSaveEmployeeChanges(@ModelAttribute Employee employee){

        basicEmployeeService.save(employee.getBasicInfo());
        employeeService.save(employee);
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
        model.addAttribute("roles", roleService.findAll());
        return "hr-approve-edit-employee";
    }



    @GetMapping("/employee/{id}/new-contract")
    public String createNewContract(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/employee";
        }

        model.addAttribute("employee", maybeEmployee.get());
        model.addAttribute("contract", new Contract());
        return "hr-contract-create";
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

        return "redirect:/employee-portal/hr/employee/" + employee.getId();
    }


}
