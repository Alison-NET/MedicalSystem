package com.alisonnet.medicalsystem.employeeportal.entity.account;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "specimen_pick_up_day_times")
public class SpecimenPickUpDayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<PickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private Account account;
}
