package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;


import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "updated_specimen_pick_up_day_times")
public class UpdatedSpecimenPickUpDayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<UpdatedPickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private UpdatedAccount account;
}
