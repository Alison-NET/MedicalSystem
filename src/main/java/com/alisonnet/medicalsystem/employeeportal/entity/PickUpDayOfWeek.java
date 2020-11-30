package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes")
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "pick_up_days_of_week")
public class PickUpDayOfWeek {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;
}
