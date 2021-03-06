package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode(exclude = "employee")
@ToString(exclude = "employee")
@Table(name = "credentials")
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Email
    private String email;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String password;

    @OneToOne(mappedBy = "credentials")
    private Employee employee;
}
