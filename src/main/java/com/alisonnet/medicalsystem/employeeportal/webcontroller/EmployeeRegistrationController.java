package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/new-employee")
@AllArgsConstructor
@Slf4j
public class EmployeeRegistrationController {

    BasicEmployeeService basicEmployeeService;

    DepartmentService departmentService;

    @GetMapping
    public String getRegistrationPage(Model model){
        model.addAttribute("basicEmployee", new BasicEmployee());
        model.addAttribute("departments", departmentService.findAll());
        return "employee-registration";
    }

    @PostMapping
    public String handleRegisterRequest(@ModelAttribute BasicEmployee basicEmployee, Model model){
        basicEmployeeService.save(basicEmployee);
        model.addAttribute("message", Constants.THANKS_FOR_REGISTER);
        return "action-result";
    }

}
