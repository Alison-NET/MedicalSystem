package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.*;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales/account")
@Slf4j
public class SalesEmployeeAccountManagementController {

    AccountService accountService;
    ProviderService providerService;
    TitleService titleService;
    PickUpDayOfWeekService pickUpDayOfWeekService;
    PickUpTimeService pickUpTimeService;

    private void setupAccountNeededAttributes(Account account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping
    public String getAllAccounts(Model model){
        model.addAttribute("accounts", accountService.findAll());
        return "sales/accounts";
    }

    @GetMapping("/{id}")
    public String getAccountByIdInfoPage(@PathVariable int id, HttpServletRequest request, Model model){

        Optional<Account> mbAccount = accountService.findById(id);

        if(mbAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        model.addAttribute("account", mbAccount.get());
        return "sales/account-info";
    }

    @GetMapping("/new")
    public String getAccountRegistrationPage(Model model){

        Account account = new Account();

        account.setProviders(new ArrayList<>());

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
        List<PickUpDayOfWeek> daysOfWeek = pickUpDayOfWeekService.findAllByOrderByIdAsc();
        daysOfWeek.forEach(dayOfWeek->{
            SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
            specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
            specimenPickUpDayTime.setPickUpDayOfWeek(dayOfWeek);
            specimenPickUpDayTimes.add(specimenPickUpDayTime);
        });
        account.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

        setupAccountNeededAttributes(account, model);
        return "sales/account-registration";
    }


    @GetMapping("/edit/{id}")
    public String getAccountByIdEditPage(@PathVariable int id, HttpServletRequest request, Model model){
        Optional<Account> maybeAccount = accountService.findById(id);

        if(maybeAccount.isEmpty())
            return Optional.ofNullable(request.getHeader("Referer"))
                    .map(requestUrl -> "redirect:" + requestUrl)
                    .orElse("/");

        setupAccountNeededAttributes(maybeAccount.get(),model);
        return "sales/account-registration";
    }


    @PostMapping(value = "/new", params = {"addProvider"})
    private String addProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.add(new Provider());

        setupAccountNeededAttributes(account, model);
        return "sales/account-registration";
    }


    @PostMapping(value = "/new", params = {"removeProvider"})
    private String removeProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.remove(providers.size() - 1);

        setupAccountNeededAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping("/new/add-pick-up-time")
    private String addPickUpTime(@ModelAttribute Account account, @RequestParam("dayId") int dayId, Model model){

        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId-1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new PickUpTime());

        setupAccountNeededAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping("/new/remove-pick-up-time")
    private String removePickUpTime(@ModelAttribute Account account, @RequestParam("dayId") int dayId, Model model){

        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTimes().get(dayId-1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.remove(pickUpTimes.size() - 1);

        setupAccountNeededAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping("/save")
    private String handleAccountSaving(@ModelAttribute Account account){

        Optional<Account> maybeAccount = accountService.findById(account.getId());

        log.info("Tuesday PickUpTimes before: " + maybeAccount.get().getSpecimenPickUpDayTimes().get(1).getPickUpTimes().size());

//        If account existed before
        maybeAccount.ifPresent(value -> removeDeletedItems(account, value));

        account.getProviders().forEach(provider -> provider.setAccount(account));
        account.getSpecimenPickUpDayTimes().forEach(specimenPickUpDayTime -> {
                specimenPickUpDayTime.setAccount(account);
                specimenPickUpDayTime.getPickUpTimes().forEach(
                        pickUpTime -> pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime)); }
        );
        accountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }

    private void removeDeletedItems(@ModelAttribute Account account, Account accountBefore) {
//            If number of providers has changed
//        if(accountBefore.getProviders().size() != account.getProviders().size()){
//            List<Provider> providersToRemove = new ArrayList<>(accountBefore.getProviders());
//            account.getProviders().forEach(providersToRemove::remove);
//            // remove provider that is no longer in updated account
//            providersToRemove.stream().forEach(provider -> {
//                List<Provider> providers = account.getProviders();
//                providers.remove(provider);
//
//                log.info(providerService.findAll().toString());
//                log.info(providerService.findById(provider.getId()).get().toString());
//                providerService.remove(providerService.findById(provider.getId()).get());
//                log.info(providerService.findAll().toString());
//            });
//        }

        for(int i = 0; i < pickUpDayOfWeekService.findAll().size();i++) {
//                If number of pick up times for specific (i) day changed
            log.info(accountBefore.getSpecimenPickUpDayTimes().get(i).getPickUpDayOfWeek().getName());
            log.info("PickUpTimes before: " + accountBefore.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size());
            log.info("PickUpTimes now: " + account.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size());

            if (accountBefore.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size() !=
                    account.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().size()) {
                List<PickUpTime> pickUpTimesToRemove =
                        new ArrayList<>(accountBefore.getSpecimenPickUpDayTimes().get(i).getPickUpTimes());
                account.getSpecimenPickUpDayTimes().get(i).getPickUpTimes().forEach(pickUpTimesToRemove::remove);
                log.info("pickUpTimesToRemove" + pickUpTimesToRemove.toString());
                // remove PickUpTime that is no longer in updated account
                pickUpTimesToRemove.stream().forEach(pickUpTime -> {
                    log.info(pickUpTime.toString());
                    pickUpTimeService.remove(pickUpTime);
                });
            }
        }
    }
}
