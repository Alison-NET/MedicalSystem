package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "intended_documents")
@ToString(exclude = "jobPosition")
public class IntendedDocument extends Document{

    @ManyToOne
    private JobPosition jobPosition;
}
