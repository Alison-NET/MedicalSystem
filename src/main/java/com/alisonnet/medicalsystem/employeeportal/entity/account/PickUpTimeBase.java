package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@MappedSuperclass
@Data
public abstract class PickUpTimeBase {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;
}
