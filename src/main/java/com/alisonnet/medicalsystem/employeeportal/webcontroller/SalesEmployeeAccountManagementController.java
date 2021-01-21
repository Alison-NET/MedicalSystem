package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredAccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.updated.UpdatedAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales/account")
@Slf4j
public class SalesEmployeeAccountManagementController {

    TitleService titleService;
    AccountService accountService;
    ConversionService conversionService;
    PickUpDayOfWeekService pickUpDayOfWeekService;

    UnregisteredAccountService unregisteredAccountService;
    UpdatedAccountService updatedAccountService;

    @GetMapping
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "accounts";
    }

    @GetMapping("/{id}")
    public String getAccountByIdInfoPage(@PathVariable int id, HttpServletRequest request, Model model){

        Optional<Account> mbAccount = accountService.findById(id);

        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        model.addAttribute("account", mbAccount.get());
        return "account-info";
    }


    // ========= CREATING UNREGISTERED ACCOUNT =========

    private void setupUnregisteredAccountNeededAttrs(UnregisteredAccount account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("createUnregistered", true);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping("/new")
    public String getAccountRegPage(Model model){
        setupUnregisteredAccountNeededAttrs(unregisteredAccountService.createUnregisteredAccount(), model);
        return "account-setup-new";
    }


    @PostMapping("/new/save")
    private String handleUnregisteredAccountSaving(@Valid @ModelAttribute UnregisteredAccount account,
                                                   BindingResult bindingResult,
                                                   Model model){
        if(bindingResult.hasErrors()){
            setupUnregisteredAccountNeededAttrs(account, model);
            return "account-setup-new";
        }

        unregisteredAccountService.fillNeededData(account);
        unregisteredAccountService.fillUniqueIds(account);
        unregisteredAccountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }

    // =============================================
    // ========= CREATING UPDATED ACCOUNT =========


    private void setupUpdatedAccountNeededAttrs(UpdatedAccount account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("createUpdated", true);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping("/update/{id}")
    private String getUpdateAccountPage(@PathVariable int id, HttpServletRequest request, Model model){
        Optional<Account> mbAccount = accountService.findById(id);
        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        UpdatedAccount updatedAccount = conversionService.convert(mbAccount.get(), UpdatedAccount.class);

        setupUpdatedAccountNeededAttrs(updatedAccount, model);
        return "account-setup-new";
    }

    @PostMapping("/update/save")
    private String handleUpdatedAccountSaving(@Valid @ModelAttribute UpdatedAccount account,
                                              BindingResult bindingResult,
                                              Model model){

        if(bindingResult.hasErrors()){
            setupUpdatedAccountNeededAttrs(account, model);
            return "account-setup-new";
        }

        updatedAccountService.fillNeededData(account);
        updatedAccountService.fillUniqueIds(account);
        updatedAccountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }
}
