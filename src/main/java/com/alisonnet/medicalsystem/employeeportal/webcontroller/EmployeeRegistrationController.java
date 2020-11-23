package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.repository.TitleRepo;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import com.alisonnet.medicalsystem.employeeportal.service.BasicEmployeeService;
import com.alisonnet.medicalsystem.employeeportal.service.JobPositionService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/new-employee")
@AllArgsConstructor
@Slf4j
public class EmployeeRegistrationController {

    BasicEmployeeService basicEmployeeService;
    TitleService titleService;

    @GetMapping
    public String getRegistrationPage(Model model){
        model.addAttribute("basicEmployee", new BasicEmployee());
        model.addAttribute("titles", titleService.findAll());
        return "employee-registration";
    }

    @PostMapping
    public String handleRegisterRequest(@ModelAttribute @Valid BasicEmployee basicEmployee,
                                        BindingResult bindingResult,
                                        Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("titles", titleService.findAll());
            return "employee-registration";
        }
        basicEmployeeService.registerNewBasicEmployee(basicEmployee);
        model.addAttribute("message", Constants.BASIC_EMPLOYEE_THANK_FOR_REG);
        return "action-result";
    }

}
