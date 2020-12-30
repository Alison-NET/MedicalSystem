package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.Employee;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"employees"})
@Table(name = "work_shifts")
public class WorkShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private LocalTime beginning;

    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private LocalTime ending;

    @OneToMany(mappedBy = "workShift", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @Override
    public String toString() {
        return beginning.format(DateTimeFormatter.ofPattern(Constants.WORK_TIME_FORMAT)) + " - " +
                ending.format(DateTimeFormatter.ofPattern(Constants.WORK_TIME_FORMAT));
    }
}
