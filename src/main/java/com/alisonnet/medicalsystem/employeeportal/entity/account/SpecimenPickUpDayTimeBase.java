package com.alisonnet.medicalsystem.employeeportal.entity.account;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class SpecimenPickUpDayTimeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
