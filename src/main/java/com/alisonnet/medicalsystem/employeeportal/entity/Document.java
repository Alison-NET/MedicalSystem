package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "documents")
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