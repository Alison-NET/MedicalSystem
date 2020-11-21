package com.alisonnet.medicalsystem.employeeportal.entity;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = { "subordinates", "documents"})
@ToString(exclude = { "subordinates", "documents"})
@Table(name = "employees")
@Slf4j
public class Employee {

    @Id
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    BasicEmployee basicInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;

    @ManyToOne
    private JobPosition jobPosition;

    private Double salary;

    private Double commission;

    @ManyToOne(cascade = CascadeType.ALL)
    private WorkShift workShift;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    private String jobDescription;

    private Boolean completedTraining;

    private Boolean addedToPayroll;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Document> documents;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;

    public void removeFromDepartmentRelations() {
        if(isDepartmentChief()){
            subordinates.forEach(subordinate -> {
                subordinate.setSupervisor(null);
            });
            return;
        }
        if(subordinates!=null) {
            subordinates.forEach(subordinate -> {
                subordinate.setSupervisor(supervisor);
            });
        }
        if(supervisor!=null){
            supervisor = null;
        }
    }

    public boolean isDepartmentChief(){
        return jobPosition.getName().toLowerCase().contains("Department Chief".toLowerCase());
    }
}
