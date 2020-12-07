package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes")
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "accounts")
public class Account {

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

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<Provider> providers;

    private Boolean providerPortal;

    private Boolean paperRequisitions;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;

    private Boolean approved;

}
