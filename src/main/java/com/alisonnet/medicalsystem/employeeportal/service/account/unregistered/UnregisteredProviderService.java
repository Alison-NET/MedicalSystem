package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered;

import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UnregisteredProviderService {
    List<UnregisteredProvider> findAll();
    Optional<UnregisteredProvider> findById(int id);
    UnregisteredProvider save(UnregisteredProvider unregisteredProvider);
    void remove(UnregisteredProvider unregisteredProvider);
    int getMaxId();
}
