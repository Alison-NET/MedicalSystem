package com.alisonnet.medicalsystem.employeeportal.api;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import com.alisonnet.medicalsystem.employeeportal.service.TitleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api" + Constants.URL_EMPLOYEE_PORTAL + "/title")
public class TitleController {
    private final TitleService titleService;

    @GetMapping
    public List<Title> getAllTitles(){
        return titleService.findAllByOrderByIdAsc();
    }
}
