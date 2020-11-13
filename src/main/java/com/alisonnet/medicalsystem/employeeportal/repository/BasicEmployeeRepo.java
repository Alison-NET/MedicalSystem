package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.BasicEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasicEmployeeRepo extends JpaRepository<BasicEmployee, Integer> {

    BasicEmployee findFirstByPersonalEmail(String personalEmail);

}
