package com.alisonnet.medicalsystem.employeeportal.service.employee.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.employee.Credentials;
import com.alisonnet.medicalsystem.employeeportal.repository.employee.CredentialsRepo;
import com.alisonnet.medicalsystem.employeeportal.service.employee.CredentialsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    CredentialsRepo credentialsRepo;

    @Override
    public Optional<Credentials> findByEmail(String email) {
        return credentialsRepo.findByEmail(email);
    }

    @Override
    public boolean hasUniqueEmail(Credentials credentials) {
        return credentialsRepo.findAll().stream()
                .filter(credents -> credents.getId() != credentials.getId())
                .noneMatch(credens -> credens.getEmail().toUpperCase().equals(credentials.getEmail().toUpperCase()));
    }
}
