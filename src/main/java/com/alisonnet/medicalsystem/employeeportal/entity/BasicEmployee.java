package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String firstName;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String middleName;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String addressFirst;

    private String addressSecond;

    private String city;

    private String state;

    private String ZIP;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    @Pattern(regexp="(^$|[0-9]{10})", message = Constants.VALID_MSG_PATTERN)
    private String homePhone;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    @Pattern(regexp="(^$|[0-9]{10})", message = Constants.VALID_MSG_PATTERN)
    private String mobilePhone;

    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String personalEmail;

    // Add regex pattern
    @NotBlank(message = Constants.VALID_MSG_NOT_EMPTY)
    private String socialSecurity;

    @OneToOne(mappedBy = "basicInfo", cascade = CascadeType.ALL)
    private Employee fullInfo;
}
