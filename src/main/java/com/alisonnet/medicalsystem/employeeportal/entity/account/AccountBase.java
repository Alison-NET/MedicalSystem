package com.alisonnet.medicalsystem.employeeportal.entity.account;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class AccountBase {

    @Id
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

    private Boolean providerPortal;

    private Boolean paperRequisitions;
}
