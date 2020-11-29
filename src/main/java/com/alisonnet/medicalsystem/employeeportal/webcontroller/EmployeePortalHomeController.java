package com.alisonnet.medicalsystem.employeeportal.webcontroller;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Department;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import com.alisonnet.medicalsystem.employeeportal.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@AllArgsConstructor
@RequestMapping(Constants.URL_EMPLOYEE_PORTAL)
@Slf4j
public class EmployeePortalHomeController {

    DepartmentService departmentService;

    @GetMapping
    public String getHomePage(Model model){

        List<Department> departmentsWithoutChiefs = departmentService.getDepartmentsWithoutChiefs();

        log.info(departmentsWithoutChiefs.toString());
        //HR
        model.addAttribute("departmentsWithoutChief", departmentsWithoutChiefs );

        return "employee-portal-home";
    }

}
