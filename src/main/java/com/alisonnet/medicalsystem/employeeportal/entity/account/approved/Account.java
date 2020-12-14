package com.alisonnet.medicalsystem.employeeportal.entity.account.approved;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.updated.UpdatedAccount;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes", callSuper = true)
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "accounts")
public class Account extends AccountBase {

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Provider> providers;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<SpecimenPickUpDayTime> specimenPickUpDayTimes;

    @OneToMany(mappedBy = "baseVersion")
    private List<UpdatedAccount> updatedVersions;
}
