package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpDayOfWeekBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes", callSuper = true)
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "unregistered_pick_up_days_of_week")
public class UnregisteredPickUpDayOfWeek extends PickUpDayOfWeekBase {

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UnregisteredSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
