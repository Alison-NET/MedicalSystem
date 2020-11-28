package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.IntendedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntendedDocumentRepo extends JpaRepository<IntendedDocument, Integer> {
}
