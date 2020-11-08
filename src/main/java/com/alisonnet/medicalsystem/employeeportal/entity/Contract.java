package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String contractLink;
    private Boolean signed;
    private Date starting;
    private Date expiring;

    @ManyToOne
    private Employee employee;
}
