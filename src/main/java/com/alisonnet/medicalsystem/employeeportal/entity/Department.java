package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "department")
    private List<JobPosition> jobPositions;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
}
