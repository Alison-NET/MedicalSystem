package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
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

    private String contactName;

    private String contactEmail;

    @OneToMany(mappedBy = "account")
    private List<Provider> providers;

    private Boolean providerPortal;

    private Boolean paperRequisitions;

    @ManyToOne(cascade = {CascadeType.ALL})
    private SpecimenPickUpDayTime specimenPickUpDayTime;

    private Boolean approved;

}
