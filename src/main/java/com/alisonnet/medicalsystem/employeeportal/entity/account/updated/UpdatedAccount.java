package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(exclude = {"specimenPickUpDayTimes", "baseVersion"}, callSuper = true)
@ToString(exclude = {"specimenPickUpDayTimes", "baseVersion"})
@Table(name = "updated_accounts")
public class UpdatedAccount extends AccountBase {

    @Valid
    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UpdatedProvider> providers;

    @Valid
    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<UpdatedSpecimenPickUpDayTime> specimenPickUpDayTimes;

    @ManyToOne
    private Account baseVersion;
}
