package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(exclude = {"empDocuments"})
@ToString(exclude = {"empDocuments"})
@Table(name = "document_types")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "documentType", fetch = FetchType.LAZY)
    private List<EmpDocument> empDocuments;
}
