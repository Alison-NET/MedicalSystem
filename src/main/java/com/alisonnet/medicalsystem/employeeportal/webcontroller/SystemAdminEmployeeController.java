package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/admin")
@AllArgsConstructor
@Slf4j
public class SystemAdminEmployeeController {

    AccountService accountService;

    @GetMapping
    public String getAdminPage(Model model)
    {
        return "admin-control";
    }

//  ===============  ACCOUNTS MANAGEMENT  ================

    @GetMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable int id){
        accountService.remove(accountService.findById(id).get());
        return "redirect:/employee-portal/sales/account";
    }

//  ======================================================


}
