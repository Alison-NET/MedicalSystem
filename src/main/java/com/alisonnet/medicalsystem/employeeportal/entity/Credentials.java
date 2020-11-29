package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "credentials")
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    @NotEmpty
    @NotNull
    private String email;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    @NotNull
    private String password;

    @OneToOne(mappedBy = "credentials")
    private Employee employee;
}
