package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
public abstract class ProviderBase {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Title title;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String firstName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String lastName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String email;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.NPI_PATTERN)
    private String NPI;
}
