package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "accounts")
@ToString(exclude = "accounts")
@Table(name = "specimen_pick_up_day_times")
public class SpecimenPickUpDayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specimenPickUpDayTime")
    private List<Account> accounts;

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = CascadeType.ALL)
    private List<PickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;
}
