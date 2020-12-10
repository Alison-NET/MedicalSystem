package com.alisonnet.medicalsystem.employeeportal.entity.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "appointed_documents")
@ToString(exclude = "jobPosition")
public class AppointedDocument extends Document{

    @ManyToOne
    private JobPosition jobPosition;
}
