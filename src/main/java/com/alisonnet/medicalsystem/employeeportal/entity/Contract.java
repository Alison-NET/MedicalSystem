package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starting;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiring;

    @ManyToOne
    private Employee employee;
}
