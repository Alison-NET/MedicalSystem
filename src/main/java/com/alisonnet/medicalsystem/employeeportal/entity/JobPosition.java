package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(
        exclude = {"department"})
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue
    private String name;

    @ManyToOne
    Department department;
}
