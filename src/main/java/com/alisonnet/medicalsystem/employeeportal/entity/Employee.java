package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(
        exclude = {"department"})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String middleName;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String homePhone;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobilePhone;

    private String personalEmail;

    private String workEmail;

    @ManyToOne
    private Department department;

    private String socialSecurity;

    private Double salary;

    private String workingHours;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contract;

    @OneToMany
    private List<Employee> subordinates;

    private String jobDescription;

    private Boolean completedTraining;

    private Boolean addedToPayroll;

    private String w4;

}
