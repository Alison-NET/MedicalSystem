package com.alisonnet.medicalsystem.employeeportal.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "documents")
//@ToString(exclude = {"employee"})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String extension;

    @Lob
    private byte[] data;
}
