package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeek;
import com.alisonnet.medicalsystem.employeeportal.entity.account.SpecimenPickUpDayTimeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "updated_specimen_pick_up_day_times")
public class UpdatedSpecimenPickUpDayTime extends SpecimenPickUpDayTimeBase {

    @OneToMany(mappedBy = "specimenPickUpDayTime", cascade = {CascadeType.ALL})
    private List<UpdatedPickUpTime> pickUpTimes;

    @ManyToOne
    private PickUpDayOfWeek pickUpDayOfWeek;

    @ManyToOne
    private UpdatedAccount account;
}
