package com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.impl;

import com.alisonnet.medicalsystem.employeeportal.entity.account.PickUpTimeBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.ProviderBase;
import com.alisonnet.medicalsystem.employeeportal.entity.account.unregistered.UnregisteredProvider;
import com.alisonnet.medicalsystem.employeeportal.repository.account.unregistered.UnregisteredProviderRepo;
import com.alisonnet.medicalsystem.employeeportal.service.account.unregistered.UnregisteredProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UnregisteredProviderServiceImpl implements UnregisteredProviderService {

    UnregisteredProviderRepo unregisteredProviderRepo;

    @Override
    public List<UnregisteredProvider> findAll() {
        return unregisteredProviderRepo.findAll();
    }

    @Override
    public Optional<UnregisteredProvider> findById(int id) {
        return unregisteredProviderRepo.findById(id);
    }

    @Override
    public UnregisteredProvider save(UnregisteredProvider unregisteredProvider) {
        return unregisteredProviderRepo.save(unregisteredProvider);
    }

    @Override
    public void remove(UnregisteredProvider unregisteredProvider) {
        unregisteredProviderRepo.delete(unregisteredProvider);
    }

    @Override
    public int getMaxId() {
        return unregisteredProviderRepo.findTopByOrderByIdDesc().map(ProviderBase::getId).orElse(0);
    }
}
