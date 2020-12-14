package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpDayOfWeekService;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/admin/account")
@Slf4j
public class SystemAdminEmployeeAccountManagementController {

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

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id){
        accountService.remove(accountService.findById(id).get());
        return "redirect:/employee-portal/sales/account";
    }




    // ========= UNREGISTERED ACCOUNT APPROVING =========

    private void setupUnregisteredAccountNeededAttrs(UnregisteredAccount account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("approveUnregistered", true);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping("/approve-unregistered")
    public String getAllUnregisteredAccounts(Model model){
        model.addAttribute("accounts", unregisteredAccountService.findAll());
        model.addAttribute("approveUnregistered", true);
        return "system-admin/unapproved-accounts";
    }

    @GetMapping("/approve-unregistered/{id}")
    public String getAccountRegPage(@PathVariable int id, Model model){
        Optional<UnregisteredAccount> mbUnregisteredAccount = unregisteredAccountService.findById(id);
        if(mbUnregisteredAccount.isEmpty()){
            model.addAttribute("unregistered", true);
            return "system-admin/unapproved-accounts";
        }

        setupUnregisteredAccountNeededAttrs(mbUnregisteredAccount.get(), model);
        return "account-registration";
    }

    @PostMapping("/approve-unregistered/add-provider")
    private String addProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.add(new UnregisteredProvider());

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }


    @PostMapping("/approve-unregistered/rm-provider")
    private String removeProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }

    @PostMapping("/approve-unregistered/add-pick-up-time")
    private String addPickUpTime(@ModelAttribute UnregisteredAccount account,
                                 @RequestParam("dayId") int dayId,
                                 Model model){

        unregisteredAccountService.addPickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }


    @PostMapping("/approve-unregistered/rm-pick-up-time")
    private String removePickUpTime(@ModelAttribute UnregisteredAccount account,
                                    @RequestParam("dayId") int dayId,
                                    Model model){

        unregisteredAccountService.removePickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-registration";
    }



    @PostMapping("/approve-unregistered/save")
    private String handleAccountSaving(@ModelAttribute UnregisteredAccount account){

        // CONVERT TO ACCOUNT

        return "redirect:/employee-portal/sales/account";
    }

    // =============================================

}
