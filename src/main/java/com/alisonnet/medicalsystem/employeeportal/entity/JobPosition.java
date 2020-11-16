package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "jobPosition")
    private List<Employee> employee;

    @ManyToOne
    private Department department;
}
