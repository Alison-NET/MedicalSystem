package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import com.alisonnet.medicalsystem.employeeportal.entity.account.SpecimenPickUpDayTimeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "unregistered_specimen_pick_up_day_times")
public class UnregisteredSpecimenPickUpDayTime extends SpecimenPickUpDayTimeBase {

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<UnregisteredPickUpTime> pickUpTimes;

    @ManyToOne
    private UnregisteredPickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private UnregisteredAccount account;
}
