package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "departments")
@EqualsAndHashCode(exclude = {"employees", "jobPositions"})
@ToString(exclude = {"employees", "jobPositions"})
public class Department {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "department")
    private List<JobPosition> jobPositions;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
