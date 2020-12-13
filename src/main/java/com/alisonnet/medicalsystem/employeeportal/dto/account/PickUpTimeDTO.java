package com.alisonnet.medicalsystem.employeeportal.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickUpTimeDTO {
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;
}
