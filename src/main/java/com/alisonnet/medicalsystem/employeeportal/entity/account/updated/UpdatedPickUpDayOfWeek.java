package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

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
@Table(name = "updated_pick_up_days_of_week")
public class UpdatedPickUpDayOfWeek extends PickUpDayOfWeekBase {

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UpdatedSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
