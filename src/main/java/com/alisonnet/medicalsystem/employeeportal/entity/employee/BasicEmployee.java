package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(exclude = { "fullInfo"})
@ToString(exclude = { "fullInfo"})
@Table(name = "basic_employees")
public class BasicEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Title title;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String firstName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String middleName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private Date dateOfBirth;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String addressFirst;

    private String addressSecond;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String city;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String state;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.ZIP_PATTERN, message = Constants.VALIDATION_MSG_INVALID_ZIP)
    private String ZIP;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.PHONE_PATTERN, message = Constants.VALIDATION_MSG_INVALID_PHONE)
    private String homePhone;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.PHONE_PATTERN, message = Constants.VALIDATION_MSG_INVALID_PHONE)
    private String mobilePhone;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Email
    private String personalEmail;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.SSN_PATTERN, message = Constants.VALIDATION_MSG_INVALID_SNN)
    private String socialSecurity;

    @OneToOne(mappedBy = "basicInfo", cascade = CascadeType.ALL)
    private Employee fullInfo;
}
