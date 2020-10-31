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
@EqualsAndHashCode(exclude = {"contract", "subordinates", "documents"})
@ToString(exclude = {"contract", "subordinates", "documents"})
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    BasicEmployee basicInfo;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String workEmail;

    private Double salary;

    private Double commission;

    @ManyToOne
    private WorkShift workShift;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contract;

    @OneToMany
    private List<Employee> subordinates;

    private String jobDescription;

    private Boolean completedTraining;

    private Boolean addedToPayroll;

    private String w4;

    @OneToMany(mappedBy = "employee")
    private List<Document> documents;

    @ManyToOne
    private Role role;

}
