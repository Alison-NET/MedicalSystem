package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@Table(name = "unregistered_accounts")
public class UnregisteredAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UnregisteredProvider> providers;

    private Boolean providerPortal;

    private Boolean paperRequisitions;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<UnregisteredSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
