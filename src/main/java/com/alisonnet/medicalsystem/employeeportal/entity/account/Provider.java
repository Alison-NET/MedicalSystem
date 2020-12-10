package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "account")
@ToString(exclude = "account")
@Table(name = "providers")
public class Provider {
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
    private Account account;
}
