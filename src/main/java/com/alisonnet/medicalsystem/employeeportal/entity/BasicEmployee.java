package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Data
@Table(name = "basic_employees")
public class BasicEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String firstName;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String middleName;

    @NotBlank(message = Constants.NOT_EMPTY)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Pattern(regexp="(^$|[0-9]{10})")
    private String homePhone;

    @NotBlank(message = Constants.NOT_EMPTY)
    @Pattern(regexp="(^$|[0-9]{10})")
    private String mobilePhone;

    private String personalEmail;

//    @ManyToOne
//    private Department department;

    private String socialSecurity;
}
