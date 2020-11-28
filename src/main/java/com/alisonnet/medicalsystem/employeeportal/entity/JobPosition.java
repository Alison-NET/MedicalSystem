package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employees"})
@ToString(exclude = {"employees"})
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "jobPosition", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "jobPosition")
    private List<IntendedDocument> intendedDocuments;
}
