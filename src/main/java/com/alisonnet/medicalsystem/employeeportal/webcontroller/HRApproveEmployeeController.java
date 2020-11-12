package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import com.alisonnet.medicalsystem.employeeportal.service.RoleService;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr/approve-employee")
@Slf4j
public class HRApproveEmployeeController {

    EmployeeService employeeService;
    BasicEmployeeService basicEmployeeService;
    DepartmentService departmentService;
    RoleService roleService;

    @GetMapping
    public String getEmployeesToApprove(Model model){
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
        return "approve-requests";
    }

    @GetMapping("/{id}")
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
        return "approve-employee";
    }

    @PostMapping("/save")
    public String handleApprovedEmployee(@ModelAttribute Employee employee){
        basicEmployeeService.save(employee.getBasicInfo());
        employeeService.save(employee);
        return "redirect:/employee-portal/hr/approve-employee";
    }

    @GetMapping("/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){
        basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
    }
}
