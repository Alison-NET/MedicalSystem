package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String link;

    private Boolean signed;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starting;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiring;

    @ManyToOne//(cascade = CascadeType.MERGE)
    private Employee employee;
}
