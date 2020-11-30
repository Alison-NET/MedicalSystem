package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.AppointedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointedDocumentRepo extends JpaRepository<AppointedDocument, Integer> {
}
