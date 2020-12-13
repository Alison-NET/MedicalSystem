package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.SpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredSpecimenPickUpDayTime;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedSpecimenPickUpDayTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"specimenPickUpDayTimes", "unregisteredSpecimenPickUpDayTimes", "updatedSpecimenPickUpDayTimes"})
@ToString(exclude = {"specimenPickUpDayTimes", "unregisteredSpecimenPickUpDayTimes", "updatedSpecimenPickUpDayTimes"})
@Table(name = "pick_up_days_of_week")
public class PickUpDayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UnregisteredSpecimenPickUpDayTime> unregisteredSpecimenPickUpDayTimes;

    @OneToMany(mappedBy = "pickUpDayOfWeek")
    private List<UpdatedSpecimenPickUpDayTime> updatedSpecimenPickUpDayTimes;
}
