package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Account;
import com.alisonnet.medicalsystem.employeeportal.entity.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.service.AccountService;
import com.alisonnet.medicalsystem.employeeportal.service.PickUpDayOfWeekService;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
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
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/sales/account/new")
@Slf4j
public class SalesEmployeeAccountSetupController {

    AccountService accountService;
    TitleService titleService;
    PickUpDayOfWeekService pickUpDayOfWeekService;


    @GetMapping
    public String getAccountRegistrationPage(Model model){

        Account account = new Account();

        account.setProviders(new ArrayList<>());

        List<SpecimenPickUpDayTime> specimenPickUpDayTimes = new ArrayList<>();
        List<PickUpDayOfWeek> daysOfWeek = pickUpDayOfWeekService.findAllByOrderByIdAsc();
        log.info(daysOfWeek.toString());
        daysOfWeek.forEach(dayOfWeek->{
            SpecimenPickUpDayTime specimenPickUpDayTime = new SpecimenPickUpDayTime();
            specimenPickUpDayTime.setPickUpTimes(new ArrayList<>());
            specimenPickUpDayTime.setPickUpDayOfWeek(dayOfWeek);
            specimenPickUpDayTimes.add(specimenPickUpDayTime);
        });

        account.setSpecimenPickUpDayTimes(specimenPickUpDayTimes);

        log.info(specimenPickUpDayTimes.toString());

        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

    @PostMapping(params = {"addProvider"})
    private String addProvider(@ModelAttribute Account account, Model model){

        log.info("On ADD PROVIDER: " + account.getSpecimenPickUpDayTimes().toString());

        List<Provider> providers = account.getProviders();
        providers.add(new Provider());
        account.setProviders(providers);

        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

    @PostMapping(params = {"removeProvider"})
    private String removeProvider(@ModelAttribute Account account, Model model){

        List<Provider> providers = account.getProviders();
        providers.remove(providers.size()-1);
        account.setProviders(providers);

        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

    @PostMapping(params = {"addPickUpTime"})
    private String addPickUpTime(@ModelAttribute Account account, Model model){

        log.info("addPickUpTime: " + account.getSpecimenPickUpDayTimes().toString());

//        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
//        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
//        pickUpTimes.add(new PickUpTime());
//
//        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
//        account.setSpecimenPickUpDayTime(specimenPickUpDayTime);

        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

    @PostMapping(params = {"removePickUpTime"})
    private String removePickUpTime(@ModelAttribute Account account, Model model){

//        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
//        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
//        pickUpTimes.remove(pickUpTimes.size()-1);
//
//        specimenPickUpDayTime.setPickUpTimes(pickUpTimes);
//        account.setSpecimenPickUpDayTime(specimenPickUpDayTime);

        model.addAttribute("account", account);
        model.addAttribute("titles", titleService.findAll());
        model.addAttribute("maxProviders", Constants.MAX_PROVIDERS_PER_ACCOUNT);
        model.addAttribute("maxPickUps", Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
        return "sales/account-registration";
    }

    @PostMapping
    private String handleAccountRegistration(@ModelAttribute Account account){

//        SpecimenPickUpDayTime specimenPickUpDayTime = account.getSpecimenPickUpDayTime();
//        List<PickUpTime> pickUpTimes = specimenPickUpDayTime.getPickUpTimes();
//        pickUpTimes.forEach(pickUpTime -> pickUpTime.setSpecimenPickUpDayTime(specimenPickUpDayTime));

        accountService.save(account);
        return "redirect:/index";
    }
}
