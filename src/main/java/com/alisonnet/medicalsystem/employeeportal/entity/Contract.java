package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(
        exclude = {"employee"})
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue
    private int id;
    private String contractLink;
    private Boolean signed;
    private Date starting;
    private Date expiring;

    @ManyToOne
    private Employee employee;
}
