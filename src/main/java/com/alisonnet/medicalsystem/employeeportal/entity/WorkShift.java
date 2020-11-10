package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"employee"})
@Table(name = "work_shifts")
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = Constants.WORK_TIME_FORMAT)
    private LocalTime beginning;

    @DateTimeFormat(pattern = Constants.WORK_TIME_FORMAT)
    private LocalTime ending;

    @OneToMany(mappedBy = "workShift", fetch = FetchType.LAZY)
    private List<Employee> employee;

    @Override
    public String toString() {
        return beginning.format(DateTimeFormatter.ofPattern(Constants.WORK_TIME_FORMAT)) + " - " +
                ending.format(DateTimeFormatter.ofPattern(Constants.WORK_TIME_FORMAT));
    }
}
