package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "credentials")
public class Credentials {
    @Id
    private String email;
    private String password;

    @ManyToMany
    private List<Role> roles;
}
