package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.account.*;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredAccount;
import com.alisonnet.medicalsystem.employeeportal.service.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.*;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredAccountService;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredPickUpTimeService;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredProviderService;
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

//  ============  SERVICES  ============

    TitleService titleService;

    AccountService accountService;
    ProviderService providerService;
    PickUpDayOfWeekService pickUpDayOfWeekService;
    PickUpTimeService pickUpTimeService;


    UnregisteredAccountService unapprAccountService;
    UnregisteredProviderService unapprProviderService;
    UnregisteredPickUpDayOfWeekService unapprPickUpDayOfWeekService;
    UnregisteredPickUpTimeService unapprPickUpTimeService;


//  ====================================

    List<PickUpTimeDTO> pickUpTimesToRemove = new ArrayList<>();

    private void setupAccountNeededAttributes(AccountDTO account, Model model) {
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

        UnregisteredAccount unregisteredAccount = new UnregisteredAccount();
        unregisteredAccount.setProviders(new ArrayList<>());


//        Account account = new Account();
//        account.setProviders(new ArrayList<>());
//        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
//        List<PickUpDayOfWeek> daysOfWeek = pickUpDayOfWeekService.findAllByOrderByIdAsc();
//        daysOfWeek.forEach(dayOfWeek->{
//            SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
//            specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
//            specimenPickUpDayTime.setPickUpDayOfWeek(dayOfWeek);
//            specimenPickUpDayTimes.add(specimenPickUpDayTime);
//        });
//        account.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

        setupAccountNeededAttributes(accountDTO, model);
        return "sales/account-registration";
    }


    @GetMapping("/edit/{id}")
    public String getAccountByIdEditPage(@PathVariable int id, HttpServletRequest request, Model model){
//        Optional<Account> mbAccount = accountService.findById(id);
//
//        if(mbAccount.isEmpty())
//            return Optional.ofNullable(request.getHeader("Referer"))
//                    .map(requestUrl -> "redirect:" + requestUrl)
//                    .orElse("/");
//
//        setupAccountNeededAttributes(mbAccount.get(),model);
        return "sales/account-registration";
    }


    @PostMapping(value = "/save", params = {"addProvider"})
    private String addProvider(@ModelAttribute AccountDTO accountDTO, Model model){

        List<ProviderDTO> providers = accountDTO.getProviders();
        providers.add(new ProviderDTO());

        setupAccountNeededAttributes(accountDTO, model);
        return "sales/account-registration";
    }


    @PostMapping(value = "/save", params = {"removeProvider"})
    private String removeProvider(@ModelAttribute AccountDTO accountDTO, Model model){

        List<ProviderDTO> providers = accountDTO.getProviders();
        providers.remove(providers.size() - 1);


        setupAccountNeededAttributes(accountDTO, model);
        return "sales/account-registration";
    }

    @PostMapping("/add-pick-up-time")
    private String addPickUpTime(@ModelAttribute AccountDTO accountDTO, @RequestParam("dayId") int dayId, Model model){

        SpecimenPickUpDayTimeDTO specimenPickUpDayTime = accountDTO.getSpecimenPickUpDayTimes().get(dayId-1);
        List<PickUpTimeDTO> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new PickUpTimeDTO());


        setupAccountNeededAttributes(accountDTO, model);
        return "sales/account-registration";
    }

    @PostMapping("/remove-pick-up-time")
    private String removePickUpTime(@ModelAttribute AccountDTO accountDTO, @RequestParam("dayId") int dayId, Model model){

        SpecimenPickUpDayTimeDTO specimenPickUpDayTime = accountDTO.getSpecimenPickUpDayTimes().get(dayId-1);
        List<PickUpTimeDTO> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        PickUpTimeDTO pickUpTimeToRemove = pickUpTimes.remove(pickUpTimes.size() - 1);
        pickUpTimesToRemove.add(pickUpTimeToRemove);

        setupAccountNeededAttributes(accountDTO, model);
        return "sales/account-registration";
    }

    @PostMapping("/save")
    private String handleAccountSaving(@ModelAttribute Account account){

//        pickUpTimesToRemove.forEach(pickUpTime -> pickUpTimeService.remove(pickUpTime));
//        account.getProviders().forEach(provider -> provider.setAccount(account));
//        account.getSpecimenPickUpDayTimes().forEach(specimenPickUpDayTime -> {
//                specimenPickUpDayTime.setAccount(account);
//                specimenPickUpDayTime.getPickUpTimes().forEach(
//                        pickUpTime -> pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime)); }
//        );
//        accountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }
}
