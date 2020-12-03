package com.alisonnet.medicalsystem.employeeportal.service.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.Provider;
import com.alisonnet.medicalsystem.employeeportal.repository.ProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private ProviderRepo providerRepo;

    @Override
    public Provider save(Provider provider) {
        return providerRepo.save(provider);
    }

    @Override
    public void remove(Provider provider) {
        providerRepo.delete(provider);
    }
}
