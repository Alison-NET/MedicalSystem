package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales")
@Slf4j
public class SalesEmployeeController {

    AccountService accountService;
    TitleService titleService;
    PickUpDayOfWeekService pickUpDayOfWeekService;

    @GetMapping
    public String getSalesControlPage(){
        return "sales/control";
    }

    @GetMapping("/account")
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "sales/accounts";
    }


}
