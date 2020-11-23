package com.alisonnet.medicalsystem.employeeportal.repository;

import com.alisonnet.medicalsystem.employeeportal.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepo extends JpaRepository<Title, Integer> {
}
