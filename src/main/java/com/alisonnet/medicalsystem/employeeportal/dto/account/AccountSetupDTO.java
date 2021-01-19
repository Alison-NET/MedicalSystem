package com.alisonnet.medicalsystem.employeeportal.dto.account;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountSetupDTO {
    private List<Title> titles;
    private int maxProviders;
    private int maxPickUpTimes;
}
