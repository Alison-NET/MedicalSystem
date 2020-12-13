package com.alisonnet.medicalsystem.employeeportal.entity.account.approved;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.SpecimenPickUpDayTimeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "specimen_pick_up_day_times")
public class SpecimenPickUpDayTime extends SpecimenPickUpDayTimeBase {

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<PickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private Account account;
}
