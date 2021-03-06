package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTimeBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTime", callSuper = true)
@ToString(exclude = "specimenPickUpDayTime")
@Table(name = "unregistered_pick_up_times")
public class UnregisteredPickUpTime extends PickUpTimeBase {

    @ManyToOne
    private UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime;
}
