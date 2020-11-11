package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Contract;
import com.alisonnet.medicalsystem.employeeportal.entity.Credentials;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.repository.CredentialsRepo;
import com.alisonnet.medicalsystem.employeeportal.service.ContractService;
import com.alisonnet.medicalsystem.employeeportal.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL + "/employee")
@AllArgsConstructor
@Slf4j
public class EmployeeController {

    EmployeeService employeeService;
    CredentialsRepo credentialsRepo;
    ContractService contractService;


    @GetMapping()
    public String getAllEmployees(Model model){
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployeeProfileById(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/employee";
        }
        log.info(maybeEmployee.get().toString());
        model.addAttribute("employee", maybeEmployee.get());
        return "employee-profile";
    }

    @GetMapping("/to-profile")
    public String getEmployeeProfile(){
        Optional<Employee> maybeEmployee =
                Optional.ofNullable(getActiveEmployee(SecurityContextHolder.getContext().getAuthentication()));
        if(maybeEmployee.isEmpty()){
            return "redirect:/index";
        }
        return "redirect:/employee-portal/employee/" + maybeEmployee.get().getId();
    }

    public Employee getActiveEmployee(Authentication authentication){
        Optional<Authentication> maybeAuthentication = Optional.ofNullable(authentication);
        if(maybeAuthentication.isEmpty()){
            return null;
        }
        Optional<Credentials> maybeCredentials =
                credentialsRepo.findByEmail(maybeAuthentication.get().getName());
        if(maybeCredentials.isEmpty()){
            return null;
        }
        Optional<Employee> maybeEmployee =
                employeeService.findFirstByCredentials(maybeCredentials.get());

        return maybeEmployee.orElse(null);
    }

    @GetMapping("/{id}/new-contract")
    public String createNewContract(@PathVariable int id, Model model){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/employee";
        }

        model.addAttribute("employee", maybeEmployee.get());
        model.addAttribute("contract", new Contract());
        return "contract-create";
    }


    @PostMapping("/{id}/new-contract")
    public String handleContractCreationRequest(@PathVariable int id,
                                                @ModelAttribute Contract contract){

        Optional<Employee> maybeEmployee = employeeService.findById(id);

        if(maybeEmployee.isEmpty()){
            return "redirect:/employee-portal/employee";
        }
        Employee employee = maybeEmployee.get();
        contract.setEmployee(employee);
        contractService.save(contract);

        return "redirect:/employee-portal/employee/" + employee.getId();
    }
}
