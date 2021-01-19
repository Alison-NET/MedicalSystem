package com.alisonnet.medicalsystem.employeeportal.api;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.dto.account.AccountSetupDTO;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api" + Constants.URL_EMPLOYEE_PORTAL + "/account")
public class AccountController {

    private final TitleService titleService;

    @GetMapping("/setup")
    public AccountSetupDTO getAccountSetupConstants(){
        return new AccountSetupDTO(titleService.findAllByOrderByIdAsc(),
                Constants.MAX_PROVIDERS_PER_ACCOUNT,
                Constants.MAX_PICK_UP_TIME_AMOUNT_PER_ACCOUNT);
    }

}
