package com.alisonnet.medicalsystem.employeeportal.entity.account;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
public abstract class AccountBase {

    @Id
    private int id;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String name;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String addressFirst;

    private String addressSecond;

    private String city;

    private String state;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.ZIP_PATTERN, message = Constants.VALIDATION_MSG_INVALID_ZIP)
    private String ZIP;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.PHONE_PATTERN, message = Constants.VALIDATION_MSG_INVALID_PHONE)
    private String phone;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.FAX_PATTERN, message = Constants.VALIDATION_MSG_INVALID_FAX)
    private String fax;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Pattern(regexp = Constants.DIRECT_LINE_PATTERN, message = Constants.VALIDATION_MSG_INVALID_DIRECT_LINE)
    private String directLine;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String contactFirstName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private String contactLastName;

    @NotBlank(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    @Email
    private String contactEmail;

    private Boolean providerPortal;

    private Boolean paperRequisitions;
}
