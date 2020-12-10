package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.entity.account.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"basicEmployees", "providers"})
@ToString(exclude = {"basicEmployees", "providers"})
@Table(name = "titles")
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<BasicEmployee> basicEmployees;

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<Provider> providers;
}
