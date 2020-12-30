package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import com.alisonnet.medicalsystem.employeeportal.constant.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = { "subordinates", "empDocuments"})
@ToString(exclude = { "subordinates", "empDocuments"})
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

    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private Double salary;

    @NotNull(message = Constants.VALIDATION_MSG_CANT_BE_EMPTY)
    private Double commission;

    @ManyToOne(cascade = CascadeType.ALL)
    private WorkShift workShift;

    @OneToMany(mappedBy = "employee")
    private List<Contract> contracts;

    private Boolean completedTraining;

    private Boolean addedToPayroll;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<EmpDocument> empDocuments;

    @OneToOne(cascade = CascadeType.ALL)
    private Credentials credentials;

    public void removeFromDepartmentRelations() {
        if(isDepartmentChief()){
            subordinates.forEach(subordinate -> {
                subordinate.setSupervisor(null);
            });
            return;
        }
        if(subordinates != null) {
            subordinates.forEach(subordinate -> {
                subordinate.setSupervisor(supervisor);
            });
        }
        if(supervisor != null){
            supervisor = null;
        }
    }

    public boolean isDepartmentChief(){
        return jobPosition.equals(jobPosition.getDepartment().getChiefJobPosition());
    }
}
