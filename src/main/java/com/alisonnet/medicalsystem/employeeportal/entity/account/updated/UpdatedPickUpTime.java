package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "updated_pick_up_times")
public class UpdatedPickUpTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @ManyToOne
    private UpdatedSpecimenPickUpDayTime specimenPickUpDayTime;
}
