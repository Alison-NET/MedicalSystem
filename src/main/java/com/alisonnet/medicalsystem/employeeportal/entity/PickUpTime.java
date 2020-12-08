package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTime")
@ToString(exclude = "specimenPickUpDayTime")
@Table(name = "pick_up_times")
public class PickUpTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime pickUpTime;

    @ManyToOne
    private SpecimenPickUpDayTime specimenPickUpDayTime;
}
