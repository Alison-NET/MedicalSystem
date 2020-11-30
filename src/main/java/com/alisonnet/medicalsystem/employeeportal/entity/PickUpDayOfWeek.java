package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "pick_up_days_of_week")
public class PickUpDayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int name;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;
}
