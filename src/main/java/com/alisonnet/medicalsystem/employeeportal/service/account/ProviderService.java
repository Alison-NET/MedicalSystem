package com.alisonnet.medicalsystem.employeeportal.service.account;

import com.alisonnet.medicalsystem.employeeportal.entity.account.Provider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProviderService {
    List<Provider> findAll();
    Optional<Provider> findById(int id);
    Provider save(Provider provider);
    void remove(Provider provider);
}
