package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.AccountBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = "specimenPickUpDayTimes", callSuper = true)
@ToString(exclude = "specimenPickUpDayTimes")
@Table(name = "unregistered_accounts")
public class UnregisteredAccount extends AccountBase {

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<UnregisteredProvider> providers;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.ALL})
    private List<UnregisteredSpecimenPickUpDayTime> specimenPickUpDayTimes;
}
