package com.alisonnet.medicalsystem.employeeportal.entity.account.approved;

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
@Table(name = "pick_up_times")
public class PickUpTime extends PickUpTimeBase {

    @ManyToOne
    private SpecimenPickUpDayTime specimenPickUpDayTime;
}
