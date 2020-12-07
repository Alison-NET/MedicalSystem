package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.*;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.ProviderService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales/account/new")
@Slf4j
public class SalesEmployeeAccountSetupController {

    AccountService accountService;
    ProviderService providerService;
    TitleService titleService;
    PickUpDayOfWeekService pickUpDayOfWeekService;

    private void setupAccountRegistrationAttributes(@ModelAttribute Account account, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAllByOrderByIdAsc());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

    @GetMapping
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

        setupAccountRegistrationAttributes(account, model);
        return "sales/account-registration";
    }


    @PostMapping(params = {"addProvider"})
    private String addProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.add(new Provider());

        setupAccountRegistrationAttributes(account, model);
        return "sales/account-registration";
    }


    @PostMapping(params = {"removeProvider"})
    private String removeProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        Provider providerRemoved = providers.remove(providers.size() - 1);
        providerService.remove(providerRemoved);

        setupAccountRegistrationAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping("/add-pick-up-time")
    private String addPickUpTimeSec(@ModelAttribute Account account, @RequestParam("dayId") int dayId, Model model){

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = account.getSpecimenPickUpDayTimes();
        SpecimenPickUpDayTime specimenPickUpDayTime = specimenPickUpDayTimes.get(dayId-1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new PickUpTime());

        setupAccountRegistrationAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping("/remove-pick-up-time")
    private String removePickUpTime(@ModelAttribute Account account, @RequestParam("dayId") int dayId, Model model){

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = account.getSpecimenPickUpDayTimes();
        SpecimenPickUpDayTime specimenPickUpDayTime = specimenPickUpDayTimes.get(dayId-1);
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.remove(pickUpTimes.size() - 1);

        setupAccountRegistrationAttributes(account, model);
        return "sales/account-registration";
    }

    @PostMapping
    private String handleAccountSaving(@ModelAttribute Account account){

        ac



        account.getProviders().forEach(provider -> provider.setAccount(account));
        account.getSpecimenPickUpDayTimes().forEach(specimenPickUpDayTime -> {
                specimenPickUpDayTime.setAccount(account);
                specimenPickUpDayTime.getPickUpTimes().forEach(
                        pickUpTime -> pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime));
        });
        accountService.save(account);
        return "redirect:/employee-portal/sales/account";
    }
}
