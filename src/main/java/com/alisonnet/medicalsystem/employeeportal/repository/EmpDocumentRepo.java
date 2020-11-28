package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.EmpDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpDocumentRepo extends JpaRepository<EmpDocument, Integer> {
}
