package com.alisonnet.medicalsystem.employeeportal.entity.account.updated;

import com.alisonnet.medicalsystem.employeeportal.entity.account.ProviderBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "account", callSuper = true)
@ToString(exclude = "account")
@Table(name = "updated_providers")
public class UpdatedProvider extends ProviderBase {

    @ManyToOne
    private UpdatedAccount account;
}
