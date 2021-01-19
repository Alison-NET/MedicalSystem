package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.entity.account.approved.Provider;
import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<BasicEmployee> basicEmployees;

    @JsonIgnore
    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    private List<Provider> providers;
}
