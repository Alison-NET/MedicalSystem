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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
