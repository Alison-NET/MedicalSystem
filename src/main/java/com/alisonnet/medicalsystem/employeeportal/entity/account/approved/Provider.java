package com.alisonnet.medicalsystem.employeeportal.entity.account.approved;

import com.alisonnet.medicalsystem.employeeportal.entity.account.ProviderBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "account", callSuper = true)
@ToString(exclude = "account")
@Table(name = "providers")
public class Provider extends ProviderBase {

    @ManyToOne
    private Account account;
}
