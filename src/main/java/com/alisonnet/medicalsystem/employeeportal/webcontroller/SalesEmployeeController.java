package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.PickUpTime;
import com.alisonnet.medicalsystem.employeeportal.entity.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpDayOfWeekService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales")
@Slf4j
public class SalesEmployeeController {

    AccountService accountService;
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

    @GetMapping("/new-account")
    public String getAccountRegistrationPage(Model model){

        Account account = new Account();

        account.setProviders(new ArrayList<>());
        SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
        specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
        account.setSpecimenPickUpDayTime(specimenPickUpDayTime);

        model.addAttribute("account", account);
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("daysOfWeek", pickUpDayOfWeekService.findAll());
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-setup";
    }

    @PostMapping(value = "/new-account", params = {"addProvider"})
    private String addProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.add(new Provider());
        account.setProviders(providers);

        model.addAttribute("account", account);
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("daysOfWeek", pickUpDayOfWeekService.findAll());
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-setup";
    }

    @PostMapping(value = "/new-account", params = {"removeProvider"})
    private String removeProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.remove(providers.size()-1);
        account.setProviders(providers);

        model.addAttribute("account", account);
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("daysOfWeek", pickUpDayOfWeekService.findAll());
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-setup";
    }

    @PostMapping(value = "/new-account", params = {"addPickUpTime"})
    private String addPickUpTime(@ModelAttribute Account account, Model model){

        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.add(new PickUpTime());

        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
        account.setSpecimenPickUpDayTime(specimenPickUpDayTime);

        model.addAttribute("account", account);
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("daysOfWeek", pickUpDayOfWeekService.findAll());
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-setup";
    }

    @PostMapping(value = "/new-account", params = {"removePickUpTime"})
    private String removePickUpTime(@ModelAttribute Account account, Model model){

        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.remove(pickUpTimes.size()-1);

        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
        account.setSpecimenPickUpDayTime(specimenPickUpDayTime);

        model.addAttribute("account", account);
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("daysOfWeek", pickUpDayOfWeekService.findAll());
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-setup";
    }

    @PostMapping("/new-account")
    private String handleAccountRegistration(@ModelAttribute Account account){

        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
        pickUpTimes.forEach(pickUpTime -> pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime));

        accountService.save(account);
        return "redirect:/index";
    }

}
