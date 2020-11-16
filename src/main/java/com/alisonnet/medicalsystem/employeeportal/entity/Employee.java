package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = { "subordinates", "documents"})
@ToString(exclude = { "subordinates", "documents"})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.MERGE)
    BasicEmployee basicInfo;

    @ManyToOne
    private JobPosition jobPosition;

    private Double salary;

    private Double commission;

    @ManyToOne(cascade = CascadeType.ALL)
    private WorkShift workShift;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    @ManyToOne
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    private String jobDescription;

    private Boolean completedTraining;

    private Boolean addedToPayroll;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Document> documents;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;
}
