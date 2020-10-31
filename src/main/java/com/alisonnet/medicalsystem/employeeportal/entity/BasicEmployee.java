package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "basic_employees")
public class BasicEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String middleName;

    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    //    @Pattern(regexp="(^$|[0-9]{10})")
    private String homePhone;

    //    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobilePhone;

    private String personalEmail;

    private String workEmail;

    @ManyToOne
    private Department department;

    private String socialSecurity;
}
