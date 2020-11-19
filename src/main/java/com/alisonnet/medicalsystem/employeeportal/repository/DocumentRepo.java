package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.Document;
import com.alisonnet.medicalsystem.employeeportal.entity.DocumentType;
import com.alisonnet.medicalsystem.employeeportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DocumentRepo extends JpaRepository<Document, Integer> {

}
