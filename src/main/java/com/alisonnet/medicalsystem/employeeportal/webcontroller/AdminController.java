package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/admin")
@AllArgsConstructor
@Slf4j
public class AdminController {

    @GetMapping
    public String getAdminPage(Model model)
    {
        return "admin-control";
    }

}
