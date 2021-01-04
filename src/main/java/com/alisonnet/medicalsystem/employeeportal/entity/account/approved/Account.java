package com.alisonnet.medicalsystem.employeeportal.entity.account.approved;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes", callSuper = true)
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "accounts")
public class Account extends AccountBase {

    @Valid
    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Provider> providers;

    @Valid
    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;

    @OneToMany(mappedBy = "baseVersion", fetch = FetchType.LAZY)
    private List<UpdatedAccount> updatedVersions;
}
