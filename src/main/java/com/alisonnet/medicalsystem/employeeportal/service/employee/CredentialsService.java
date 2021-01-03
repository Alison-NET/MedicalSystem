package com.alisonnet.medicalsystem.employeeportal.service.employee;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Credentials;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CredentialsService {
    Optional<Credentials> findByEmail(String email);
    boolean hasUniqueEmail(Credentials credentials);
}
