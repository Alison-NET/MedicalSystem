package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employee"})
@ToString(exclude = {"employee"})
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String link;

    private Boolean signed;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private Date starting;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private Date expiring;

    @ManyToOne//(cascade = CascadeType.MERGE)
    private Employee employee;
}
