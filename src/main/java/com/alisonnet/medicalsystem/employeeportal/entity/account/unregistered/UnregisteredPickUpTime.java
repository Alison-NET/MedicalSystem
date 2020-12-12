package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "unregistered_pick_up_times")
public class UnregisteredPickUpTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @ManyToOne
    private UnregisteredSpecimenPickUpDayTime specimenPickUpDayTime;
}
