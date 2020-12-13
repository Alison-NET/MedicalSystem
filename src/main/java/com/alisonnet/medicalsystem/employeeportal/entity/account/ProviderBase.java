package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class ProviderBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Title title;

    private String firstName;

    private String lastName;

    private String email;

    private String NPI;
}
