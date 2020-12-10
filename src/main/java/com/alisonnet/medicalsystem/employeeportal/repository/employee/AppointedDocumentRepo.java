package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.AppointedDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointedDocumentRepo extends JpaRepository<AppointedDocument, Integer> {
}
