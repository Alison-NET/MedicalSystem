package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "unregistered_providers")
public class UnregisteredProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Title title;

    private String firstName;

    private String lastName;

    private String email;

    private String NPI;

    @ManyToOne
    private UnregisteredAccount account;

}
