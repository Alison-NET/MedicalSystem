package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "documents")
//@ToString(exclude = {"documentType"})
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String docName;
    private String docExtension;

    @ManyToOne
    private DocumentType documentType;

    @Lob
    private byte[] data;

    @ManyToOne
    private Employee employee;
}
