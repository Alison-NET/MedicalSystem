package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.EmpDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpDocumentRepo extends JpaRepository<EmpDocument, Integer> {
}
