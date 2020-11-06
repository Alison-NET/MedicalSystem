package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    EmployeeService employeeService;

    @GetMapping()
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

}
