package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "speciment_pick_up_day_times")
public class SpecimenPickUpDayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specimenPickUpDayTime")
    private List<Account> account;

    @OneToMany(mappedBy = "specimenPickUpDayTime")
    private List<PickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;
}
