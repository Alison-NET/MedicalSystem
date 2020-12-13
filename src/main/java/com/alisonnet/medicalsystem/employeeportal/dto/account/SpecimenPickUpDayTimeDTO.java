package com.alisonnet.medicalsystem.employeeportal.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecimenPickUpDayTimeDTO {
    private int id;

    List<PickUpTimeDTO> pickUpTimes;
    PickUpDayOfWeekDTO pickUpDayOfWeek;
}
