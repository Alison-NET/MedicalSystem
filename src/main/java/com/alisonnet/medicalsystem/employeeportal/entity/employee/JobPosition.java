package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"employees"})
@ToString(exclude = {"employees"})
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "jobPosition", fetch = FetchType.LAZY)
    private List<Employee> employees;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "jobPosition")
    private List<AppointedDocument> intendedDocuments;

    public String toAuthority(){
        return department.toAuthority() + "_" + name.replaceAll("\\s+","_").toUpperCase() + "_POS";
    }
}
