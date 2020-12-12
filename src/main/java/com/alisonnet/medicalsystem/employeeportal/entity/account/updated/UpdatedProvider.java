package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "updated_providers")
public class UpdatedProvider {

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
    private UpdatedAccount account;

}
