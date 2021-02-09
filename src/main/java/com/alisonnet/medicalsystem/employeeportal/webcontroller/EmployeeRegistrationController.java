package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import com.alisonnet.medicalsystem.employeeportal.service.employee.BasicEmployeeService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import com.alisonnet.medicalsystem.employeeportal.validator.BasicEmployeePersonalEmailValidator;
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

    private final BasicEmployeeService basicEmployeeService;
    private final TitleService titleService;

    private final BasicEmployeePersonalEmailValidator basicEmployeePersonalEmailValidator;

    @GetMapping
    public String getRegistrationPage(Model model){
        model.addAttribute("basicEmployee", new BasicEmployee());
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        return "employee-registration";
    }

    @PostMapping
    public String handleRegisterRequest(@ModelAttribute @Valid BasicEmployee basicEmployee,
                                        BindingResult bindingResult,
                                        Model model){
        basicEmployeePersonalEmailValidator.setFieldPath("personalEmail");
        basicEmployeePersonalEmailValidator.validate(basicEmployee, bindingResult);
        if(bindingResult.hasErrors()){
            model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
            return "employee-registration";
        }
        basicEmployeeService.save(basicEmployee);
        model.addAttribute("message", Constants.BASIC_EMPLOYEE_THANKS_FOR_REG_MSG);
        return "action-result";
    }

}
