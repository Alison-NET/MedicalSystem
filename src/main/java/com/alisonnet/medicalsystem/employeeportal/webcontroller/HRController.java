package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.entity.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.entity.WorkShift;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/hr")
@Slf4j
public class HRController {

    BasicEmployeeService basicEmployeeService;

    @GetMapping("/approve-employee")
    public String getEmployeesToApprove(Model model){
        model.addAttribute("employeesToApprove", basicEmployeeService.findAll());
        return "approve-requests";
    }

    @GetMapping("/approve-employee/{id}")
    public String getApprovePage(@PathVariable int id, Model model){

        Optional<BasicEmployee> maybeBasicEmployee = basicEmployeeService.findById(id);
        log.info(maybeBasicEmployee.get().toString());
        if(maybeBasicEmployee.isEmpty()){
            return "redirect:/employee-portal/hr/approve-employee";
        }

        Employee newEmployee = new Employee();

        newEmployee.setBasicInfo(maybeBasicEmployee.get());

        WorkShift workShift = new WorkShift();
        newEmployee.setWorkShift(workShift);

        Credentials credentials = new Credentials();
        newEmployee.setCredentials(credentials);



        model.addAttribute("employee", newEmployee);

        return "approve-employee";

    }

    @PostMapping("/approve-employee/{id}")
    public String handleApprovedEmployee(@ModelAttribute Employee employee){
        return "redirect:/employee-portal/hr/approve-employee";
    }


    @GetMapping("/reject/{id}")
    public String deleteBasicEmployee(@PathVariable int id){
        basicEmployeeService.deleteById(id);
        return "redirect:/employee-portal/hr/approve-employee";
    }
}