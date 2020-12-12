package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@Table(name = "unregistered_pick_up_days_of_week")
public class UnregisteredPickUpDayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UnregisteredSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
