package com.alisonnet.medicalsystem.employeeportal.service;

import com.alisonnet.medicalsystem.employeeportal.entity.Provider;
import org.springframework.stereotype.Service;

@Service
public interface ProviderService {
    Provider save(Provider provider);
    void remove(Provider provider);
}
