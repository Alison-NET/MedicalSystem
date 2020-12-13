package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
