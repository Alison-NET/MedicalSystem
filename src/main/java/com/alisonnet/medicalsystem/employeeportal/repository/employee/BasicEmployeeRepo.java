package com.alisonnet.medicalsystem.employeeportal.repository.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasicEmployeeRepo extends JpaRepository<BasicEmployee, Integer> {
    BasicEmployee findFirstByPersonalEmail(String personalEmail);
}
