package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Entity
//@EqualsAndHashCode(
//        exclude = {"department", "workShift", "role"})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String firstName;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String middleName;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String lastName;

//    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

//    @Pattern(regexp="(^$|[0-9]{10})")
    private String homePhone;

//    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobilePhone;

    @Email
    @NotBlank(message = Constants.NOT_EMPTY)
    private String personalEmail;

    @Email
    @NotBlank(message = Constants.NOT_EMPTY)
    private String workEmail;

    @ManyToOne
    private Department department;

    private String socialSecurity;

    private Double salary;

    private Double commission;

    @ManyToOne
    private WorkShift workShift;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contract;

    @OneToMany
    private List<Employee> subordinates;

    private String jobDescription;

    private boolean completedTraining;

    private boolean addedToPayroll;

    private String w4;

    @OneToMany(mappedBy = "employee")
    private List<Document> documents;

    @ManyToOne
    private Role role;

}
