package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedProvider;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import com.alisonnet.medicalsystem.employeeportal.service.account.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.PickUpDayOfWeekService;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/admin/account")
@Slf4j
public class SystemAdminEmployeeAccountManagementController {

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
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        model.addAttribute("account", mbAccount.get());
        return "account-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id){
        accountService.remove(accountService.findById(id).get());
        return "redirect:/employee-portal/admin/account";
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
        return "unregistered-accounts";
    }

    @GetMapping("/approve-unregistered/reject/{id}")
    public String rejectUnregisteredAccount(@PathVariable int id){
        unregisteredAccountService.remove(unregisteredAccountService.findById(id).get());
        return "unregistered-accounts";
    }

    @GetMapping("/approve-unregistered/{id}")
    public String getUnregisteredAccountRegPage(@PathVariable int id, Model model){
        Optional<UnregisteredAccount> mbAccount = unregisteredAccountService.findById(id);
        if(mbAccount.isEmpty())
            return "unregistered-accounts";

        setupUnregisteredAccountNeededAttrs(mbAccount.get(), model);
        return "account-setup-new";
    }

    @PostMapping("/approve-unregistered/save")
    private String handleUnregisteredAccountApproving(@Valid @ModelAttribute UnregisteredAccount account,
                                                      BindingResult bindingResult,
                                                      Model model){
        if(bindingResult.hasErrors()){
            setupUnregisteredAccountNeededAttrs(account, model);
            return "account-setup-new";
        }

        Account convertedAccount = conversionService.convert(account, Account.class);
        accountService.fillNeededData(convertedAccount);
        accountService.fillUniqueIds(convertedAccount);
        if(accountService.save(convertedAccount)!=null)
            unregisteredAccountService.remove(account);
        return "redirect:/employee-portal/admin/account";
    }



    // ==================================================
    // ========= UPDATED ACCOUNT APPROVING =========

    private void setupUpdatedAccountNeededAttrs(UpdatedAccount account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("approveUpdated", true);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping("/approve-updated")
    public String getAllUpdatedAccounts(Model model){
        model.addAttribute("accounts", updatedAccountService.findAll());
        return "updated-accounts";
    }

    @GetMapping("/approve-updated/{id}")
    public String getUpdatedAccountRegPage(@PathVariable int id, Model model){
        Optional<UpdatedAccount> mbAccount = updatedAccountService.findById(id);
        if(mbAccount.isEmpty())
            return "updated-accounts";

        setupUpdatedAccountNeededAttrs(mbAccount.get(), model);
        return "account-setup-new";
    }

    @GetMapping("/approve-updated/reject/{id}")
    public String rejectUpdatedAccount(@PathVariable int id){
        updatedAccountService.remove(updatedAccountService.findById(id).get());
        return "unregistered-accounts";
    }


    @PostMapping("/approve-updated/save")
    private String handleUpdatedAccountApproving(@Valid @ModelAttribute UpdatedAccount account,
                                                 BindingResult bindingResult,
                                                 Model model){
        if(bindingResult.hasErrors()){
            setupUpdatedAccountNeededAttrs(account, model);
            return "account-setup-new";
        }

        Account convertedAccount = conversionService.convert(account, Account.class);
        accountService.matchIdsGenerateUniqueIfNeeded(account.getBaseVersion(), convertedAccount);
        if(accountService.save(convertedAccount)!=null){
            updatedAccountService.remove(updatedAccountService.findById(account.getId()).get());
        }
        return "redirect:/employee-portal/admin/account";
    }

    // ==================================================
    // ================ ACCOUNT CREATION ================

    private void setupAccountNeededAttrs(Account account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("updateCreate", true);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping("/new")
    public String getAccountRegPage(Model model){
        setupAccountNeededAttrs(accountService.createAccount(), model);
        return "account-setup-new";
    }

    @GetMapping("/update/{id}")
    public String getAccountUpdatePage(@PathVariable int id, Model model){
        Optional<Account> mbAccount = accountService.findById(id);
        if(mbAccount.isEmpty())
            return "accounts";

        setupAccountNeededAttrs(mbAccount.get(), model);
        return "account-setup-new";
    }

    @PostMapping("/update-create/save")
    private String handleAccountSaving(@Valid @ModelAttribute Account account,
                                       BindingResult bindingResult,
                                       Model model){

        if(bindingResult.hasErrors()){
            setupAccountNeededAttrs(account, model);
            return "account-setup-new";
        }

        Optional<Account> mbAccount = accountService.findById(account.getId());
        accountService.fillUniqueIds(account);

        if(mbAccount.isPresent()){
            accountService.remove(mbAccount.get());
            account.setId(mbAccount.get().getId());
        }

        accountService.fillNeededData(account);
        accountService.save(account);
        return "redirect:/employee-portal/admin/account";
    }



//    ===========TEST NEW==============
//
//    @GetMapping("/test")
//    public String getAccountCreateTestPage(Model model){
//        setupAccountNeededAttrs(accountService.createAccount(), model);
//        return "account-setup-new";
//    }

}
