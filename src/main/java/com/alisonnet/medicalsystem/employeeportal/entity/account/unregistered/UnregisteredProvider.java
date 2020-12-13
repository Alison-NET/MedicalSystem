package com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import com.alisonnet.medicalsystem.employeeportal.entity.account.ProviderBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "account", callSuper = true)
@ToString(exclude = "account")
@Table(name = "unregistered_providers")
public class UnregisteredProvider extends ProviderBase {

    @ManyToOne
    private UnregisteredAccount account;
}
