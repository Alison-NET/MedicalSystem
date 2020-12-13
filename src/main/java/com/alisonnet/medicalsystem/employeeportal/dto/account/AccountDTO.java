package com.alisonnet.medicalsystem.employeeportal.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private int id;

    private String name;

    private String addressFirst;

    private String addressSecond;

    private String city;

    private String state;

    private String ZIP;

    private String phone;

    private String fax;

    private String directLine;

    private String contactFirstName;

    private String contactLastName;

    private String contactEmail;

    List<ProviderDTO> providers;

    private Boolean providerPortal;

    private Boolean paperRequisitions;

    List<SpecimenPickUpDayTimeDTO> specimenPickUpDayTimes;
}
