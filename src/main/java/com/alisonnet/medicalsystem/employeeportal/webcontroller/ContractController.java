package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Contract;
import com.alisonnet.medicalsystem.employeeportal.service.ContractService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/contract")
//@AllArgsConstructor
//@Slf4j
//public class ContractController {
//
//    ContractService contractService;
//    EmployeeService employeeService;
//
//    @GetMapping
//    public String getAllContracts(Model model){
//        model.addAttribute("contracts", contractService.findAll());
//        return "contracts";
//    }
//
//    @GetMapping("/new")
//    public String createNewContract(Model model){
//        model.addAttribute("employees", employeeService.findAll());
//        model.addAttribute("contract", new Contract());
//        return "contract-create";
//    }
//
//    @PostMapping("/new")
//    public String handleCreationRequest(){
//        return "redirect:/employee-portal/employee/" + 3;
//    }
//}
