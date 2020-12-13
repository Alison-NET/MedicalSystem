package com.alisonnet.medicalsystem.employeeportal.dto.account;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {

    private int id;

    private Title title;

    private String firstName;

    private String lastName;

    private String email;

    private String NPI;
}
