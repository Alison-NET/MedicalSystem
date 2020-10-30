package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
@Table(name = "work_shifts")
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime beginning;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime ending;

    @OneToMany(mappedBy = "workShift")
    private List<Employee> employee;

}
