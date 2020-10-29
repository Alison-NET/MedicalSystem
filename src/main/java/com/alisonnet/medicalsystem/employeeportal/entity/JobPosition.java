package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(
        exclude = {"department"})
public class JobPosition {

    @Id
    @GeneratedValue
    private String name;

    @ManyToOne
    Department department;
}
