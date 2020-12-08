package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Provider;
import com.alisonnet.medicalsystem.employeeportal.repository.ProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepo providerRepo;

    @Override
    public List<Provider> findAll() {
        return providerRepo.findAll();
    }
    @Override
    public Optional<Provider> findById(int id) {
        return providerRepo.findById(id);
    }
    @Override
    public Provider save(Provider provider) {
        return providerRepo.save(provider);
    }

    @Override
    public void remove(Provider provider) {
        providerRepo.delete(provider);
    }

}
