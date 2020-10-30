package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/new-employee")
@Slf4j
public class EmployeeRegistrationController {

    @Autowired
    EmployeeRepo repository;

    @GetMapping
    public String getRegistrationPage(Model model){
        Employee employee = new Employee();
        employee.setFirstName("Taras");
        model.addAttribute("employee", employee);
        return "employee-registration";
    }

    @PostMapping
    public String handleRegisterRequest(@ModelAttribute Employee employee){
        log.info(employee.toString());
        repository.save(employee);
        return "redirect:";
    }

}
