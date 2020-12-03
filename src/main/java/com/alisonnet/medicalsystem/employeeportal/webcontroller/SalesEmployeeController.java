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

    @GetMapping("/account")
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "sales/accounts";
    }

    @GetMapping("/account/{id}")
    public String getAccountByIdInfoPage(@PathVariable int id, HttpServletRequest request, Model model){

        Optional<Account> mbAccount = accountService.findById(id);

        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        model.addAttribute("account", mbAccount.get());
        return "sales/account-info";
    }

    @GetMapping("/account/edit/{id}")
    public String getAccountByIdEditPage(@PathVariable int id, HttpServletRequest request, Model model){
        Optional<Account> mbAccount = accountService.findById(id);

        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        model.addAttribute("account", mbAccount.get());
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

}
