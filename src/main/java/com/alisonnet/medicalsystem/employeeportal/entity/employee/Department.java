package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"jobPositions","chiefJobPosition"})
@ToString(exclude = {"jobPositions","chiefJobPosition"})
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    private List<JobPosition> jobPositions;

    @OneToOne
    private JobPosition chiefJobPosition;

    public String toAuthority(){
        return name.replaceAll("\\s+","_").toUpperCase() + "_DEP";
    }
}
