package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Data
@Table(name = "pick_up_times")
public class PickUpTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime time;

    @ManyToOne
    private SpecimenPickUpDayTime specimenPickUpDayTime;
}
