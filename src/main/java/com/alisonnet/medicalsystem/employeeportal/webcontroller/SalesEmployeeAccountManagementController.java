package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales/account")
@Slf4j
public class SalesEmployeeAccountManagementController {

    TitleService titleService;
    AccountService accountService;

    // ========= ACCOUNT REGISTRATION SERVICES=========
    UnregisteredAccountService unregisteredAccountService;
    PickUpDayOfWeekService pickUpDayOfWeekService;

    @GetMapping
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "accounts";
    }

    @GetMapping("/{id}")
    public String getAccountByIdInfoPage(@PathVariable int id, HttpServletRequest request, Model model){

        Optional<Account> mbAccount = accountService.findById(id);

        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
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
        return "account-registration";
    }

    @PostMapping("/new/add-provider")
    private String addProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.add(new UnregisteredProvider());

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }


    @PostMapping("/new/rm-provider")
    private String removeProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }

    @PostMapping("/new/add-pick-up-time")
    private String addPickUpTime(@ModelAttribute UnregisteredAccount account,
                                 @RequestParam("dayId") int dayId,
                                 Model model){

        unregisteredAccountService.addPickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }


    @PostMapping("/new/rm-pick-up-time")
    private String removePickUpTime(@ModelAttribute UnregisteredAccount account,
                                    @RequestParam("dayId") int dayId,
                                    Model model){

        unregisteredAccountService.removePickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }



    @PostMapping("/new/save")
    private String handleAccountSaving(@ModelAttribute UnregisteredAccount account){
        unregisteredAccountService.fillNeededData(account);
        unregisteredAccountService.fillUniqueIds(account);
        unregisteredAccountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }

    // =============================================

}
