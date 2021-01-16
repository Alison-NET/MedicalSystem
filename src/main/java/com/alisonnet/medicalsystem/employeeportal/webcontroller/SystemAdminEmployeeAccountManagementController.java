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
        return "account-setup";
    }

    @PostMapping("/approve-unregistered/add-provider")
    private String addUnregisteredProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.add(new UnregisteredProvider());

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/approve-unregistered/rm-provider")
    private String removeUnregisteredProvider(@ModelAttribute UnregisteredAccount account, Model model){

        List<UnregisteredProvider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-setup";
    }

    @PostMapping("/approve-unregistered/add-pick-up-time")
    private String addUnregisteredPickUpTime(@ModelAttribute UnregisteredAccount account,
                                 @RequestParam("dayId") int dayId,
                                 Model model){

        unregisteredAccountService.addPickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/approve-unregistered/rm-pick-up-time")
    private String removeUnregisteredPickUpTime(@ModelAttribute UnregisteredAccount account,
                                    @RequestParam("dayId") int dayId,
                                    Model model){

        unregisteredAccountService.removePickUpTime(account, dayId);
        setupUnregisteredAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/approve-unregistered/save")
    private String handleUnregisteredAccountApproving(@Valid @ModelAttribute UnregisteredAccount account,
                                                      BindingResult bindingResult,
                                                      Model model){
        if(bindingResult.hasErrors()){
            setupUnregisteredAccountNeededAttrs(account, model);
            return "account-setup";
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
        return "account-setup";
    }

    @GetMapping("/approve-updated/reject/{id}")
    public String rejectUpdatedAccount(@PathVariable int id){
        updatedAccountService.remove(updatedAccountService.findById(id).get());
        return "unregistered-accounts";
    }

    @PostMapping("/approve-updated/add-provider")
    private String addUpdatedProvider(@ModelAttribute UpdatedAccount account, Model model){

        List<UpdatedProvider> providers = account.getProviders();
        providers.add(new UpdatedProvider());

        setupUpdatedAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/approve-updated/rm-provider")
    private String removeUpdatedProvider(@ModelAttribute UpdatedAccount account, Model model){

        List<UpdatedProvider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupUpdatedAccountNeededAttrs(account, model);
        return "account-setup";
    }

    @PostMapping("/approve-updated/add-pick-up-time")
    private String addUpdatedPickUpTime(@ModelAttribute UpdatedAccount account,
                                        @RequestParam("dayId") int dayId,
                                        Model model){

        updatedAccountService.addPickUpTime(account, dayId);
        setupUpdatedAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/approve-updated/rm-pick-up-time")
    private String removeUpdatedPickUpTime(@ModelAttribute UpdatedAccount account,
                                           @RequestParam("dayId") int dayId,
                                           Model model){

        updatedAccountService.removePickUpTime(account, dayId);
        setupUpdatedAccountNeededAttrs(account, model);
        return "account-setup";
    }

    @PostMapping("/approve-updated/save")
    private String handleUpdatedAccountApproving(@Valid @ModelAttribute UpdatedAccount account,
                                                 BindingResult bindingResult,
                                                 Model model){
        if(bindingResult.hasErrors()){
            setupUpdatedAccountNeededAttrs(account, model);
            return "account-setup";
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
        return "account-setup";
    }

    @GetMapping("/update/{id}")
    public String getAccountUpdatePage(@PathVariable int id, Model model){
        Optional<Account> mbAccount = accountService.findById(id);
        if(mbAccount.isEmpty())
            return "accounts";

        setupAccountNeededAttrs(mbAccount.get(), model);
        return "account-setup";
    }

    @PostMapping("/update-create/add-provider")
    private String addProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.add(new Provider());

        setupAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/update-create/rm-provider")
    private String removeProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupAccountNeededAttrs(account, model);
        return "account-setup";
    }

    @PostMapping("/update-create/add-pick-up-time")
    private String addPickUpTime(@ModelAttribute Account account,
                                             @RequestParam("dayId") int dayId,
                                             Model model){

        accountService.addPickUpTime(account, dayId);
        setupAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/update-create/rm-pick-up-time")
    private String removePickUpTime(@ModelAttribute Account account,
                                                @RequestParam("dayId") int dayId,
                                                Model model){

        accountService.removePickUpTime(account, dayId);
        setupAccountNeededAttrs(account, model);
        return "account-setup";
    }


    @PostMapping("/update-create/save")
    private String handleAccountSaving(@Valid @ModelAttribute Account account,
                                       BindingResult bindingResult,
                                       Model model){

        if(bindingResult.hasErrors()){
            setupAccountNeededAttrs(account, model);
            return "account-setup";
        }

        Optional<Account> mbAccount = accountService.findById(account.getId());
        if(mbAccount.isEmpty())
            accountService.fillUniqueIds(account);
        else
            accountService.fillUniqueEmptyIds(account);

        accountService.fillNeededData(account);
        accountService.save(account);
        return "redirect:/employee-portal/admin/account";
    }




}
