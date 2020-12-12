package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@Table(name = "updated_pick_up_days_of_week")
public class UpdatedPickUpDayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UpdatedSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
