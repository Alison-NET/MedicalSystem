package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "unregistered_specimen_pick_up_day_times")
public class UnregisteredSpecimenPickUpDayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<UnregisteredPickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private UnregisteredAccount account;
}
