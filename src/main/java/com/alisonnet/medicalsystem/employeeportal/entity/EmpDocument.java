package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "employee_documents")
@ToString(exclude = "employee")
public class EmpDocument extends Document {
    @ManyToOne
    private DocumentType documentType;

    private Boolean isLocked;

    @ManyToOne
    private Employee employee;
}
