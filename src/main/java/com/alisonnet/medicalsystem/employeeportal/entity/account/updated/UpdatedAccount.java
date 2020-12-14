package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes", callSuper = true)
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "updated_accounts")
public class UpdatedAccount extends AccountBase {

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UpdatedProvider> providers;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<UpdatedSpecimenPickUpDayTime> specimenPickUpDayTimes;

    @ManyToOne
    private Account baseVersion;
}
